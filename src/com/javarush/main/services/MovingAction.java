package com.javarush.main.services;

import com.javarush.main.enums.DirectionsOfMoving;
import com.javarush.main.enums.TextMessages;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.concurrent.CopyOnWriteArrayList;

public class MovingAction {
    private DirectionsOfMoving directionsOfMoving;
    private SupportingMethods supportingMethods = new SupportingMethods();
    private EnumRandomChoice enumRandomChoice = new EnumRandomChoice();

    public void makeMove(Object[][] islandInstance, CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, int row, int columns) {
        directionsOfMoving = chooseDirection();
        int travelSpeed = animal.getMaxTravelSpeed();
        if (travelSpeed == 0) {
            return;
        }
        switch (directionsOfMoving) {
            case NORTH -> moveNorth(islandInstance, entitiesOnPosition, animal, travelSpeed, row, columns);
            case SOUTH -> moveSouth(islandInstance, entitiesOnPosition, animal, travelSpeed, row, columns);
            case WEST -> moveWest(islandInstance, entitiesOnPosition, animal, travelSpeed, row, columns);
            case EAST -> moveEast(islandInstance, entitiesOnPosition, animal, travelSpeed, row, columns);
            default -> throw new IllegalStateException(TextMessages.NO_CHOOSING_DIRECTION.getMassage());
        }
        int saturationRatio = animal.getSaturationRatio();
        animal.setSaturationRatio(saturationRatio - 1);
    }

    private DirectionsOfMoving chooseDirection() {
        return enumRandomChoice.chooseRandomEnum(DirectionsOfMoving.class);
    }

    private void moveNorth(Object[][] islandInstance, CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveNorthSouth(islandInstance, entitiesOnPosition, animal, travelSpeed, row, columns);
    }

    private void moveSouth(Object[][] islandInstance, CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, int travelSpeed, int row, int columns) {
        moveNorthSouth(islandInstance, entitiesOnPosition, animal, travelSpeed, row, columns);
    }

    private void moveWest(Object[][] islandInstance, CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveWestEast(islandInstance, entitiesOnPosition, animal, travelSpeed, row, columns);
    }

    private void moveEast(Object[][] islandInstance, CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, int travelSpeed, int row, int columns) {
        moveWestEast(islandInstance, entitiesOnPosition, animal, travelSpeed, row, columns);
    }

    private void moveWestEast(Object[][] islandInstance, CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, int travelSpeed, int row, int columns) {
        int outOfBoundArray = columns + travelSpeed;
        boolean ifCanNotMove = checkPossibilityToMove(islandInstance, outOfBoundArray, columns);
        if (ifCanNotMove) {
            supportingMethods.changeActionDoneFlag(animal, true);
        } else {
            CopyOnWriteArrayList<Entity> targetList = (CopyOnWriteArrayList<Entity>) islandInstance[row][columns + travelSpeed];
            animalMovement(entitiesOnPosition, targetList, animal);
        }
    }

    private void moveNorthSouth(Object[][] islandInstance, CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, int travelSpeed, int row, int columns) {
        int outOfBoundArray = row + travelSpeed;
        boolean ifCanNotMove = checkPossibilityToMove(islandInstance, outOfBoundArray, row);
        if (ifCanNotMove) {
            supportingMethods.changeActionDoneFlag(animal, true);
        } else {
            CopyOnWriteArrayList<Entity> targetList = (CopyOnWriteArrayList<Entity>) islandInstance[row + travelSpeed][columns];
            animalMovement(entitiesOnPosition, targetList, animal);
        }
    }

    private void animalMovement(CopyOnWriteArrayList<Entity> entitiesOnPosition, CopyOnWriteArrayList<Entity> targetList, Animal animal) {
        int maxNumberOnPosition = animal.getMaxNumberOnPosition();
        int countOfSameAnimals = supportingMethods.countNumberOfSameEntityOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition) {
            entitiesOnPosition.remove(animal);
            supportingMethods.changeActionDoneFlag(animal, true);
            targetList.add(animal);
        }
    }

    private boolean checkPossibilityToMove(Object[][] islandInstance, int outOfBoundArray, int rowOrColumns) {
        return outOfBoundArray <= 0 || outOfBoundArray >= islandInstance[rowOrColumns].length;
    }
}
