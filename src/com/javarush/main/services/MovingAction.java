package com.javarush.main.services;

import com.javarush.main.enums.DirectionsOfMoving;
import com.javarush.main.enums.TextMessages;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.List;

public class MovingAction {
    DirectionsOfMoving directionsOfMoving;
    SupportingMethods supportingMethods = new SupportingMethods();
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();

    public void makeMove(Object[][] islandInstance, List<Entity> copyList, Animal animal, int row, int columns) {
        directionsOfMoving = chooseDirection();
        int travelSpeed = animal.getMaxTravelSpeed();
        if (travelSpeed == 0) {
            return;
        }
        switch (directionsOfMoving) {
            case NORTH -> moveNorth(islandInstance, copyList, animal, travelSpeed, row, columns);
            case SOUTH -> moveSouth(islandInstance, copyList, animal, travelSpeed, row, columns);
            case WEST -> moveWest(islandInstance, copyList, animal, travelSpeed, row, columns);
            case EAST -> moveEast(islandInstance, copyList, animal, travelSpeed, row, columns);
            default -> throw new IllegalStateException(TextMessages.NO_CHOOSING_DIRECTION.getMassage());
        }
        int saturationRatio = animal.getSaturationRatio();
        animal.setSaturationRatio(saturationRatio - 1);
    }

    private DirectionsOfMoving chooseDirection() {
        return enumRandomChoice.chooseRandomEnum(DirectionsOfMoving.class);
    }

    private void moveNorth(Object[][] islandInstance, List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveNorthSouth(islandInstance, copyList, animal, travelSpeed, row, columns);
    }

    private void moveSouth(Object[][] islandInstance, List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        moveNorthSouth(islandInstance, copyList, animal, travelSpeed, row, columns);
    }

    private void moveWest(Object[][] islandInstance, List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveWestEast(islandInstance, copyList, animal, travelSpeed, row, columns);
    }

    private void moveEast(Object[][] islandInstance, List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        moveWestEast(islandInstance, copyList, animal, travelSpeed, row, columns);
    }

    private void moveWestEast(Object[][] islandInstance, List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        int outOfBoundArray = columns + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= islandInstance[columns].length) {
            supportingMethods.changeActionDoneFlag(animal, true);
            return;
        }
        List<Entity> targetList = (List<Entity>) islandInstance[row][columns + travelSpeed];
        int maxNumberOnPosition = supportingMethods.maxNumberOnPosition(animal);
        int countOfSameAnimals = supportingMethods.countNumberOfSameEntityOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition) {
            copyList.remove(animal);
            supportingMethods.changeActionDoneFlag(animal, true);
            targetList.add(animal);
        }
    }

    private void moveNorthSouth(Object[][] islandInstance, List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        int outOfBoundArray = row + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= islandInstance[row].length) {
            supportingMethods.changeActionDoneFlag(animal, true);
            return;
        }
        List<Entity> targetList = (List<Entity>) islandInstance[row + travelSpeed][columns];
        int maxNumberOnPosition = animal.getMaxNumberOnPosition();
        int countOfSameAnimals = supportingMethods.countNumberOfSameEntityOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition) {
            copyList.remove(animal);
            supportingMethods.changeActionDoneFlag(animal, true);
            targetList.add(animal);
        }
    }
}
