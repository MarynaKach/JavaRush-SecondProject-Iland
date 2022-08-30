package com.javarush.main.services;

import com.javarush.main.enums.Actions;
import com.javarush.main.enums.TextMessages;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Phaser;

public class IslandEntityIterationRunnable extends Thread implements Runnable {
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();
    EatingAction eatingAction = new EatingAction();
    Reproduction reproduction = new Reproduction();
    SupportingMethods supportingMethods = new SupportingMethods();
    Actions actions;
    MovingAction movingAction = new MovingAction();
    GrassGrowth grassGrowth = new GrassGrowth();
    Object[][] islandInstance;
    Phaser phaser;
    int row;
    int columns;

    public IslandEntityIterationRunnable(Phaser phaser, Object[][] islandInstance, int row, int columns) {
        this.phaser = phaser;
        this.islandInstance = islandInstance;
        this.row = row;
        this.columns = columns;
        phaser.register();
        new Thread(this).start();
    }

    @Override
    public synchronized void run() {
        islandAnimalIteration(islandInstance, row, columns);
        phaser.arriveAndAwaitAdvance();
        killingOfHungryAnimal(islandInstance, row, columns);
        setFalseActionDone(islandInstance, row, columns);
        phaser.arriveAndAwaitAdvance();
        grassGrowth.letPlantGrowAtNight(islandInstance, "Grass", row, columns);
        phaser.arriveAndDeregister();
    }

    public synchronized void islandAnimalIteration(Object[][] islandInstance, int row, int columns) {
        CopyOnWriteArrayList<Entity> entitiesOnPosition = (CopyOnWriteArrayList<Entity>) islandInstance[row][columns];
        for (int i = 0; i < entitiesOnPosition.size(); i++) {
            Entity entity = entitiesOnPosition.get(i);
            String packageName = entity.getClass().getPackageName();
            if (!(packageName.contains("plant")) && entity != null) {
                Animal animal = (Animal) entity;
                boolean isActionDone = animal.isActionDone();
                if (!isActionDone) {
                    chooseAction(islandInstance, entitiesOnPosition, animal, row, columns);
                    i--;
                }
            }
        }
    }

    private void chooseAction(Object[][] islandInstance, CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, int row, int columns) {
        actions = enumRandomChoice.chooseRandomEnum(Actions.class);
        switch (actions) {
            case MOVE -> movingAction.makeMove(islandInstance, entitiesOnPosition, animal, row, columns);
            case EAT -> eatingAction.eat(entitiesOnPosition, animal);
            case REPRODUCE -> reproduction.reproduce(entitiesOnPosition, animal);
            default -> throw new IllegalStateException(String.valueOf(TextMessages.FAILURE_TO_CHOOSE_ACTION));
        }
    }

    private void killingOfHungryAnimal(Object[][] islandInstance, int row, int columns) {
        CopyOnWriteArrayList<Entity> entitiesOnPosition = (CopyOnWriteArrayList<Entity>) islandInstance[row][columns];
        for (int i = 0; i < entitiesOnPosition.size(); i++) {
            if (supportingMethods.ifEntityAnimal(entitiesOnPosition.get(i))) {
                Animal animal = (Animal) entitiesOnPosition.get(i);
                int saturationRatio = animal.getSaturationRatio();
                if (saturationRatio <= 0) {
                    entitiesOnPosition.remove(animal);
                    i--;
                }
            }
        }
    }

    private void setFalseActionDone(Object[][] islandInstance, int row, int columns) {
        CopyOnWriteArrayList<Entity> entitiesOnPosition = (CopyOnWriteArrayList<Entity>) islandInstance[row][columns];
        for (Entity entity : entitiesOnPosition) {
            String packageName = entity.getClass().getPackageName();
            if (!(packageName.contains("plant")) && entity != null) {
                supportingMethods.changeActionDoneFlag((Animal) entity, false);
            }
        }
    }
}
