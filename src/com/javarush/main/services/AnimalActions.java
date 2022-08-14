package com.javarush.main.services;

import com.javarush.main.enums.Actions;
import com.javarush.main.enums.DirectionsOfMoving;
import com.javarush.main.enums.TextMassages;
import com.javarush.main.game.Island;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class AnimalActions {
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();
    EntitiesProduction entitiesProduction = new EntitiesProduction();
    DirectionsOfMoving directionOfMoving;
    Actions actions;
    Object[][] island = Island.getInstance();
    int length = Island.getLength();
    int width = Island.getWidth();

    public void islandAnimalIteration() {
        for (int row = 1; row < island.length; row++) {
            for (int columns = 1; columns < island[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) island[row][columns];
                for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
                    List<Entity> copyListOfEntitiesOnPosition = listOfEntitiesOnPosition;
                    Entity entity  = (Entity) copyListOfEntitiesOnPosition.get(i);
                    String packageName = entity.getClass().getPackageName();
                    if (!(packageName.contains("plant")) && entity != null) {
                        Animal animal = (Animal) entity;
                        boolean isActionDone = animal.isActionDone();
                        if (isActionDone) {

                        } else {
                            chooseAction(copyListOfEntitiesOnPosition, animal, row, columns);
                            i--;
                        }
                    }
                    listOfEntitiesOnPosition = copyListOfEntitiesOnPosition;
                }
            }
        }
    }

    public void chooseAction(List<Entity> copyList, Entity animal, int row, int columns) {
        /*boolean isActionDone = ((Animal)animal).isActionDone();
        if (isActionDone == true) {
            return;
        }*/
        actions = enumRandomChoice.chooseRandomEnum(Actions.class);
        switch (actions) {
            case MOVE -> makeMove(copyList, animal, row, columns);
            //case EAT -> eat(animal, kgForFullSaturation, row, columns, isActionDone);
            case REPRODUCE -> reproduce(copyList, animal);
            default -> throw new IllegalStateException(String.valueOf(TextMassages.FAILURE_TO_CHOOSE_ACTION));
        }
    }

    public void makeMove(List<Entity> copyList, Entity animal, int row, int columns) {
        directionOfMoving = chooseDirection();
        int travelSpeed = getMaxNumberOnPosition(animal);
        switch (directionOfMoving) {
            case NORTH -> moveNorth(copyList, animal, travelSpeed, row, columns);
            case SOUTH -> moveSouth(copyList, animal, travelSpeed, row, columns);
            case WEST -> moveWest(copyList, animal, travelSpeed, row, columns);
            case EAST -> moveEast(copyList, animal, travelSpeed, row, columns);
            default -> throw new IllegalStateException(TextMassages.NO_CHOOSING_DIRECTION.getMassage());
        }
    }

    public DirectionsOfMoving chooseDirection() {
        return enumRandomChoice.chooseRandomEnum(DirectionsOfMoving.class);
    }

    public void moveNorth(List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveNorthSouth(copyList, animal, travelSpeed, row, columns);
    }

    public void moveSouth (List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns){
        moveNorthSouth(copyList, animal, travelSpeed, row, columns);
    }

    private void moveWest (List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns){
        travelSpeed = travelSpeed * -1;
        moveWestEast(copyList, animal, travelSpeed, row, columns);
    }

    private void moveEast (List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns){
        moveWestEast(copyList, animal, travelSpeed, row, columns);
    }

    private void eat (Entity animal,double kgForFullSaturation, int row, int columns, boolean isActionDone){
        List<Entity> list = (List<Entity>) island[row][columns];

    }
    private void reproduce (List<Entity> copyList, Entity animal){
        int maxNumberOnPosition = getMaxNumberOnPosition(animal);
        int numberOfSameAnimalOnPosition = getNumberOfSameAnimalOnPosition(copyList,animal);
        if(numberOfSameAnimalOnPosition > 1 && numberOfSameAnimalOnPosition < maxNumberOnPosition) {
            setActionDoneFlag((Animal) animal);
        } else {
            setActionDoneFlag((Animal) animal);
            Entity newBornAnimal = entitiesProduction.createNewBornAnimal(animal);
            copyList.add(newBornAnimal);
            setActionDoneFlag((Animal) animal);
        }
    }
    private void moveWestEast (List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns){
        int outOfBoundArray = row + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= width) {
            setActionDoneFlag((Animal) animal);
            return;
        }
        List<Entity> targetList = (List<Entity>) island[row][columns + travelSpeed];
        int maxNumberOnPosition = getMaxNumberOnPosition(animal);
        int countOfSameAnimals = getNumberOfSameAnimalOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition){
            copyList.remove(animal);//delete animal from current position
            setActionDoneFlag((Animal) animal); // add animal to new position
            targetList.add(animal);
        }
    }

    private void setActionDoneFlag (Animal animal) {
        animal.setActionDone(true);
}

    private void moveNorthSouth (List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns) {

        int outOfBoundArray = row + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= length) {
            setActionDoneFlag((Animal) animal);
            return;
        }
        List<Entity> targetList = (List<Entity>) island[row + travelSpeed][columns ];
        int maxNumberOnPosition = getMaxNumberOnPosition(animal);
        int countOfSameAnimals = getNumberOfSameAnimalOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition){
            copyList.remove(animal);//delete animal from current position
            setActionDoneFlag((Animal) animal); // mark the animal as action done
            targetList.add(animal);// add animal to new position
        }
    }
    private int getMaxNumberOnPosition(Entity animal) {
        Animal animalTemp = (Animal) animal;
        int maxNumberOnPosition = animalTemp.getMaxNumberOnPosition();
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
        //List<Entity> list = (List<Entity>) island[row][columns];

    }
}




