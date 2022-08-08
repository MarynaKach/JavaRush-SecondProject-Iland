package com.javarush.services;

import com.javarush.enums.Actions;
import com.javarush.enums.AnimalParametersTypes;
import com.javarush.enums.DirectionsOfMoving;
import com.javarush.enums.TextMassages;
import com.javarush.game.IslandInitialization;
import com.javarush.species.abstractclasses.Animal;
import com.javarush.species.abstractclasses.Entity;

import java.util.List;

public class AnimalActions {
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();
    IslandInitialization islandInitialization = new IslandInitialization();
    Object[][] initializedIsland = islandInitialization.getInitializedIsland();
    int length = islandInitialization.getLength();
    int width = islandInitialization.getWidth();

    public void islandAnimalIteration() {
        for (int row = 1; row < length - 1; row++) {
            for (int columns = 1; columns < width - 1; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) initializedIsland[row][columns];
                for (Entity animal : listOfEntitiesOnPosition) {
                    String packageName = animal.getClass().getPackageName();
                    if (!(packageName.contains("plant"))) {
                        try {
                            int travelSpeed = animal.getClass()
                                    .getDeclaredField(String.valueOf(AnimalParametersTypes.MAX_TRAVEL_SPEED))
                                    .getInt(animal);
                            double kgForFullSaturation = animal.getClass()
                                    .getDeclaredField(String.valueOf(AnimalParametersTypes.KG_FOR_FULL_SATURATION))
                                    .getInt(animal);
                            chooseAction(animal, travelSpeed, kgForFullSaturation, row, columns);
                        } catch (NoSuchFieldException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void chooseAction(Entity animal, int travelSpeed, double kgForFullSaturation, int row, int columns) {
        Actions actions = enumRandomChoice.chooseRandomEnum(Actions.class);
        switch (actions) {
            case MOVE -> makeMove(animal, travelSpeed, row, columns);
            case EAT -> eat(animal, kgForFullSaturation, row, columns);
            case REPRODUCE -> reproduce(animal, row, columns);
            default -> throw new IllegalStateException(String.valueOf(TextMassages.FAILURE_TO_CHOOSE_ACTION));
        }
    }

    public void makeMove(Entity animal, int travelSpeed, int row, int columns) {
        DirectionsOfMoving directionOfMoving = chooseDirection();
        switch (directionOfMoving) {
            case NORTH -> moveNorth(animal, travelSpeed, row, columns);
            case SOUTH -> moveSouth(animal, travelSpeed, row, columns);
            case WEST -> moveWest(animal, travelSpeed, row, columns);
            case EAST -> moveEast(animal, travelSpeed, row, columns);
            default -> throw new IllegalStateException(TextMassages.NO_CHOOSING_DIRECTION.getMassage());
        }
    }

    public DirectionsOfMoving chooseDirection() {
        return enumRandomChoice.chooseRandomEnum(DirectionsOfMoving.class);
    }

    public void moveNorth(Entity animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveNorthSouth(animal, travelSpeed, row, columns);
    }

    public void moveSouth (Entity animal,int travelSpeed, int row, int columns){
        moveNorthSouth(animal, travelSpeed, row, columns);
    }

    private void moveWest (Entity animal,int travelSpeed, int row, int columns){
        travelSpeed = travelSpeed * -1;
        moveWestEast(animal, travelSpeed, row, columns);
    }

    private void moveEast (Entity animal,int travelSpeed, int row, int columns){
        moveWestEast(animal, travelSpeed, row, columns);
    }

    private void eat (Entity animal,double kgForFullSaturation, int row, int columns){
        List<Entity> list = (List<Entity>) initializedIsland[row][columns];

    }
    private void reproduce (Entity animal, int row, int columns){
        List<Entity> list = (List<Entity>) initializedIsland[row][columns];
        int maxNumberOnPosition = getMaxNumberOnPosition(animal);
        int numberOfSameAnimalOnPosition = getNumberOfSameAnimalOnPosition(list,animal);
        if(numberOfSameAnimalOnPosition > 1 && numberOfSameAnimalOnPosition < maxNumberOnPosition) {
            list.add(animal);
        }
    }
    private void moveWestEast (Entity animal, int travelSpeed, int row, int columns){
        if (initializedIsland[row][columns + travelSpeed] != null) {
            List<Entity> list = (List<Entity>) initializedIsland[row][columns + travelSpeed];
            int maxNumberOnPosition = getMaxNumberOnPosition(animal);
            int countOfSameAnimals = getNumberOfSameAnimalOnPosition(list, animal);
            if (countOfSameAnimals < maxNumberOnPosition) {
                list.remove(animal);
                List<Entity> newPosition = (List<Entity>) initializedIsland[row][columns + travelSpeed];
                newPosition.add(animal);
            }
        }
    }
    private void moveNorthSouth (Entity animal, int travelSpeed, int row, int columns) {
        if (initializedIsland[row + travelSpeed][columns] != null) {
            List<Entity> list = (List<Entity>) initializedIsland[row + travelSpeed][columns];
            int maxNumberOnPosition = getMaxNumberOnPosition(animal);
            int countOfSameAnimals = getNumberOfSameAnimalOnPosition(list, animal);
            if (countOfSameAnimals < maxNumberOnPosition) {
                list.remove(animal);
                List<Entity> newPosition = (List<Entity>) initializedIsland[row + travelSpeed][columns];
                newPosition.add(animal);
            }
        }
    }
    private int getMaxNumberOnPosition(Entity animal) {
        int maxNumberOnPosition = 0;
        try {
            maxNumberOnPosition = animal.getClass()
                    .getDeclaredField(String.valueOf(AnimalParametersTypes.MAX_NUMBER_ON_POSITION))
                    .getInt(animal);

        } catch (IllegalAccessException | NoSuchFieldException e) {
            System.out.println(TextMassages.FAILURE_TO_GET_MAX_NUMBER_ON_POSITION);
            e.printStackTrace();
        }
        return maxNumberOnPosition;
    }

    private int getNumberOfSameAnimalOnPosition (List<Entity> list, Entity animal) {
        int countOfSameAnimals = 0;
        for (Entity entity : list) {
            String className = animal.getClass().getSimpleName();
            String classNamesOfList = entity.getClass().getSimpleName();
            if (className.equalsIgnoreCase(classNamesOfList)) {
                countOfSameAnimals++;
            }
        }
        return countOfSameAnimals;
    }

    private void findFood (Animal animal, int row, int columns) {
        List<Entity> list = (List<Entity>) initializedIsland[row][columns];

    }
}




