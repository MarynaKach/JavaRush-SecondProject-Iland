package com.javarush.main.services;

import com.javarush.main.enums.Actions;
import com.javarush.main.enums.TextMessages;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.*;

public class IslandEntityIteration {
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();
    EatingAction eatingAction = new EatingAction();
    Reproduction reproduction = new Reproduction();
    SupportingMethods supportingMethods = new SupportingMethods();
    Actions actions;
    MovingAction movingAction = new MovingAction();
    GrassGrowth grassGrowth = new GrassGrowth();

    public void islandAnimalIteration(Object[][] islandInstance) {
        for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) islandInstance[row][columns];
                for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
                    List<Entity> copyListOfEntitiesOnPosition = listOfEntitiesOnPosition;
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
        }
        setFalseActionDone(islandInstance);
        grassGrowth.letPlantGrowAtNight(islandInstance, "grass");
        killingOfHungryAnimal(islandInstance);
    }

    private void chooseAction(Object[][] islandInstance, List<Entity> copyList, Animal animal, int row, int columns) {
        actions = enumRandomChoice.chooseRandomEnum(Actions.class);
        switch (actions) {
            case MOVE -> movingAction.makeMove(islandInstance, copyList, animal, row, columns);
            case EAT -> eatingAction.eat(copyList, animal);
            case REPRODUCE -> reproduction.reproduce(copyList, animal);
            default -> throw new IllegalStateException(String.valueOf(TextMessages.FAILURE_TO_CHOOSE_ACTION));
        }
    }

    private void killingOfHungryAnimal(Object[][] islandInstance) {
        for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) islandInstance[row][columns];
                for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
                    List<Entity> copyList = listOfEntitiesOnPosition;
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
        }
    }

    private void setFalseActionDone(Object[][] islandInstance) {
        for (int row = 1; row < islandInstance.length; row++) {
            for (int columns = 1; columns < islandInstance[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) islandInstance[row][columns];
                for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
                    Entity animal = listOfEntitiesOnPosition.get(i);
                    String packageName = animal.getClass().getPackageName();
                    if (!(packageName.contains("plant")) && animal != null) {
                        supportingMethods.changeActionDoneFlag((Animal) animal, false);
                    }
                }
            }
        }
    }
}

