package com.javarush.main.services;

import com.javarush.main.enums.Actions;
import com.javarush.main.enums.DirectionsOfMoving;
import com.javarush.main.enums.TextMassages;
import com.javarush.main.game.Island;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

public class AnimalActions {
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();
    //IslandInitialization islandInitialization = new IslandInitialization();
    Object[][] island = Island.getInstance();
    int length = Island.getLength();
    int width = Island.getWidth();

    public void islandAnimalIteration() {
        for (int row = 1; row < island.length - 1; row++) {
            for (int columns = 1; columns < island[row].length - 1; columns++) {
                List<? extends Entity> listOfEntitiesOnPosition = (List<? extends Entity>) island[row][columns];
                List<? extends Entity> copyListOfEntitiesOnPosition = listOfEntitiesOnPosition;
                for (Entity animal : copyListOfEntitiesOnPosition) {
                    String packageName = animal.getClass().getPackageName();
                    if (!(packageName.contains("plant")) && animal != null) {
                        Animal animalTemp = (Animal) animal;
                        int travelSpeed = animalTemp.getMaxTravelSpeed();
                        double kgForFullSaturation = animalTemp.getKgForFullSaturation();
                        boolean isActionDone = animalTemp.isActionDone();
                        chooseAction(animal, travelSpeed, kgForFullSaturation, row, columns, isActionDone);
                    }
                }
            }
        }
    }

    public void chooseAction(Entity animal, int travelSpeed, double kgForFullSaturation, int row, int columns, boolean isActionDone) {
        Actions actions = enumRandomChoice.chooseRandomEnum(Actions.class);
        switch (actions) {
            case MOVE -> makeMove(animal, travelSpeed, row, columns, isActionDone);
            //case EAT -> eat(animal, kgForFullSaturation, row, columns, isActionDone);
            //case REPRODUCE -> reproduce(animal, row, columns, isActionDone);
            default -> throw new IllegalStateException(String.valueOf(TextMassages.FAILURE_TO_CHOOSE_ACTION));
        }
    }

    public void makeMove(Entity animal, int travelSpeed, int row, int columns, boolean isActionDone) {
        DirectionsOfMoving directionOfMoving = chooseDirection();
        switch (directionOfMoving) {
            case NORTH -> moveNorth(animal, travelSpeed, row, columns, isActionDone);
            case SOUTH -> moveSouth(animal, travelSpeed, row, columns, isActionDone);
            case WEST -> moveWest(animal, travelSpeed, row, columns, isActionDone);
            case EAST -> moveEast(animal, travelSpeed, row, columns, isActionDone);
            default -> throw new IllegalStateException(TextMassages.NO_CHOOSING_DIRECTION.getMassage());
        }
    }

    public DirectionsOfMoving chooseDirection() {
        return enumRandomChoice.chooseRandomEnum(DirectionsOfMoving.class);
    }

    public void moveNorth(Entity animal, int travelSpeed, int row, int columns, boolean isActionDone) {
        travelSpeed = travelSpeed * -1;
        moveNorthSouth(animal, travelSpeed, row, columns, isActionDone);
    }

    public void moveSouth (Entity animal,int travelSpeed, int row, int columns, boolean isActionDone){
        moveNorthSouth(animal, travelSpeed, row, columns, isActionDone);
    }

    private void moveWest (Entity animal,int travelSpeed, int row, int columns, boolean isActionDone){
        travelSpeed = travelSpeed * -1;
        moveWestEast(animal, travelSpeed, row, columns, isActionDone);
    }

    private void moveEast (Entity animal,int travelSpeed, int row, int columns, boolean isActionDone){
        moveWestEast(animal, travelSpeed, row, columns, isActionDone);
    }

    private void eat (Entity animal,double kgForFullSaturation, int row, int columns, boolean isActionDone){
        List<Entity> list = (List<Entity>) island[row][columns];

    }
    private void reproduce (Entity animal, int row, int columns, boolean isActionDone){
        List<Entity> list = (List<Entity>) island[row][columns];
        int maxNumberOnPosition = getMaxNumberOnPosition(animal);
        int numberOfSameAnimalOnPosition = getNumberOfSameAnimalOnPosition(list,animal);
        if(numberOfSameAnimalOnPosition > 1 && numberOfSameAnimalOnPosition < maxNumberOnPosition) {
            list.add(animal);
        }
    }
    private void moveWestEast (Entity animal, int travelSpeed, int row, int columns, boolean isActionDone){

        int outOfBoundArray = row + travelSpeed;

        if(outOfBoundArray <= 0 && outOfBoundArray > width) {

            return;
        } else {
            List<Entity> currentList = (List<Entity>) island[row][columns];
            List<Entity> targetList = (List<Entity>) island[row][columns + travelSpeed];
            Iterator<Entity> entityIterator = currentList.iterator(); //создаем итератор
            while (entityIterator.hasNext()) {//до тех пор, пока в списке есть элементы
                Entity nextEntity = entityIterator.next();//получаем следующий элемент
                int maxNumberOnPosition = getMaxNumberOnPosition(animal);
                int countOfSameAnimals = getNumberOfSameAnimalOnPosition(targetList, animal);
                if (countOfSameAnimals < maxNumberOnPosition) {
                    if (nextEntity.getClass().getName().equals(animal)) {
                        currentList.remove(animal);//удаляем
                    }
                }
                setActionDoneFlag((Animal) animal);
                targetList.add(animal);
            }
        }
    }

private void setActionDoneFlag (Animal animal) {
        animal.setActionDone(true);
}

    private void moveNorthSouth (Entity animal, int travelSpeed, int row, int columns, boolean isActionDone) {
        int outOfBoundArray = row + travelSpeed;
        if(outOfBoundArray <= 0 && outOfBoundArray > length) {
            return;
        } else
        {
            List<Entity> currentList = (List<Entity>) island[row][columns];
            List<Entity> targetList = (List<Entity>) island[row + travelSpeed][columns];
            Iterator<Entity> entityIterator = currentList.iterator();//создаем итератор
            //while (entityIterator.hasNext()) {//до тех пор, пока в списке есть элементы
                Entity nextEntity = entityIterator.next();//получаем следующий элемент
                int maxNumberOnPosition = getMaxNumberOnPosition(animal);
                int countOfSameAnimals = getNumberOfSameAnimalOnPosition(targetList, animal);
                if(countOfSameAnimals < maxNumberOnPosition) {
                    if (nextEntity.getClass().getName().equals(animal)) {
                        currentList.remove(animal);//удаляем кота с нужным именем
                    }
                }

            //List<Entity> newPosition = (List<Entity>) island[row + travelSpeed][columns];
            targetList.add(animal);
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
        List<Entity> list = (List<Entity>) island[row][columns];

    }
}




