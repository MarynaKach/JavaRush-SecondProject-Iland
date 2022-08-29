package com.javarush.main.services;

import com.javarush.main.enums.Actions;
import com.javarush.main.enums.TextMessages;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Phaser;
import java.util.concurrent.locks.ReentrantLock;

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
    ReentrantLock reentrantLock = new ReentrantLock(true);

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
        try {
            reentrantLock.lock();
            islandAnimalIteration(islandInstance, row, columns);
            phaser.arriveAndAwaitAdvance();
            killingOfHungryAnimal(islandInstance, row, columns);
            setFalseActionDone(islandInstance, row, columns);
            phaser.arriveAndAwaitAdvance();
            grassGrowth.letPlantGrowAtNight(islandInstance, "Grass", row, columns);
            phaser.arriveAndDeregister();
        } finally {
            reentrantLock.unlock();
        }
    }

    public synchronized void islandAnimalIteration(Object[][] islandInstance, int row, int columns) {
        CopyOnWriteArrayList<Entity> listOfEntitiesOnPosition = (CopyOnWriteArrayList<Entity>) islandInstance[row][columns];
        for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
            CopyOnWriteArrayList<Entity> copyListOfEntitiesOnPosition = listOfEntitiesOnPosition;
            Entity entity = copyListOfEntitiesOnPosition.get(i);
            String packageName = entity.getClass().getPackageName();
            if (!(packageName.contains("plant")) && entity != null) {
                Animal animal = (Animal) entity;
                boolean isActionDone = animal.isActionDone();
                if (!isActionDone) {
                    chooseAction(islandInstance, copyListOfEntitiesOnPosition, animal, row, columns);
                    i--;
                }
            }
            listOfEntitiesOnPosition = copyListOfEntitiesOnPosition;
        }
    }

    private void chooseAction(Object[][] islandInstance, CopyOnWriteArrayList<Entity> copyList, Animal animal, int row, int columns) {
        actions = enumRandomChoice.chooseRandomEnum(Actions.class);
        switch (actions) {
            case MOVE -> movingAction.makeMove(islandInstance, copyList, animal, row, columns);
            case EAT -> eatingAction.eat(copyList, animal);//eatingAction.eat(copyList, animal);
            case REPRODUCE -> reproduction.reproduce(copyList, animal);
            default -> throw new IllegalStateException(String.valueOf(TextMessages.FAILURE_TO_CHOOSE_ACTION));
        }
    }

    private void killingOfHungryAnimal(Object[][] islandInstance, int row, int columns) {
        CopyOnWriteArrayList<Entity> listOfEntitiesOnPosition = (CopyOnWriteArrayList<Entity>) islandInstance[row][columns];
        for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
            CopyOnWriteArrayList<Entity> copyList = listOfEntitiesOnPosition;
            Entity entity = copyList.get(i);
            boolean ifEntityAnimal = entity.getClass().getPackageName().contains("plant");
            if (!ifEntityAnimal) {
                Animal animal = (Animal) entity;
                int saturationRatio = animal.getSaturationRatio();
                if (saturationRatio <= 0) {
                    copyList.remove(animal);
                    i--;
                }
            }
            listOfEntitiesOnPosition = copyList;
        }
    }

    private void setFalseActionDone(Object[][] islandInstance, int row, int columns) {
        CopyOnWriteArrayList<Entity> listOfEntitiesOnPosition = (CopyOnWriteArrayList<Entity>) islandInstance[row][columns];
        for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
            Entity animal = listOfEntitiesOnPosition.get(i);
            String packageName = animal.getClass().getPackageName();
            if (!(packageName.contains("plant")) && animal != null) {
                supportingMethods.changeActionDoneFlag((Animal) animal, false);
            }
        }
    }
}
