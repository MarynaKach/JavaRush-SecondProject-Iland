package com.javarush.main.services;

import com.javarush.main.enums.DirectionsOfMoving;
import com.javarush.main.enums.TextMassages;
import com.javarush.main.game.Island;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.List;

public class MovingAction {
    Island island = new Island();
    DirectionsOfMoving directionsOfMoving;
    SupportingMethods supportingMethods = new SupportingMethods();
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();
    Object[][] islandInstance = island.getInstance();
    private int length = Island.length;
    private int width = Island.width;

    public void makeMove(List<Entity> copyList, Animal animal, int row, int columns) {
        directionsOfMoving = chooseDirection();
        int travelSpeed = animal.getMaxTravelSpeed();
        if (travelSpeed == 0) {
            return;
        }
        switch (directionsOfMoving) {
            case NORTH -> moveNorth(copyList, animal, travelSpeed, row, columns);
            case SOUTH -> moveSouth(copyList, animal, travelSpeed, row, columns);
            case WEST -> moveWest(copyList, animal, travelSpeed, row, columns);
            case EAST -> moveEast(copyList, animal, travelSpeed, row, columns);
            default -> throw new IllegalStateException(TextMassages.NO_CHOOSING_DIRECTION.getMassage());
        }
        int saturationRatio = animal.getSaturationRatio();
        animal.setSaturationRatio(saturationRatio - 1);
    }

    private DirectionsOfMoving chooseDirection() {
        return enumRandomChoice.chooseRandomEnum(DirectionsOfMoving.class);
    }

    private void moveNorth(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveNorthSouth(copyList, animal, travelSpeed, row, columns);
    }

    private void moveSouth(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        moveNorthSouth(copyList, animal, travelSpeed, row, columns);
    }

    private void moveWest(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveWestEast(copyList, animal, travelSpeed, row, columns);
    }

    private void moveEast(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        moveWestEast(copyList, animal, travelSpeed, row, columns);
    }

    private void moveWestEast(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        int outOfBoundArray = columns + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= width) {
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

    private void moveNorthSouth(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        int outOfBoundArray = row + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= length) {
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
