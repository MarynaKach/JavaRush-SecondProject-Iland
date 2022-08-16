package com.javarush.main.services;

import com.javarush.main.enums.Actions;
import com.javarush.main.enums.DirectionsOfMoving;
import com.javarush.main.enums.TextMassages;
import com.javarush.main.game.Island;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;


import java.util.*;

public class AnimalActions {
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();
    EntitiesProduction entitiesProduction = new EntitiesProduction();
    DirectionsOfMoving directionOfMoving;
    Actions actions;
    GrassGrowth grassGrowth = new GrassGrowth();
    Object[][] island = Island.getInstance();
    int length = Island.getLength();
    int width = Island.getWidth();
    Entity entity;

    public void islandAnimalIteration() {
        for (int row = 1; row < island.length; row++) {
            for (int columns = 1; columns < island[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) island[row][columns];
                for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
                    List<Entity> copyListOfEntitiesOnPosition = listOfEntitiesOnPosition;
                    Entity entity = copyListOfEntitiesOnPosition.get(i);
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
        setFalseIfActionDone(island);
        dyingOfHungryAnimal(island);
        letGrassGrow(island);
    }
    public void dyingOfHungryAnimal(Object[][] island) {
        for (int row = 1; row < island.length; row++) {
            for (int columns = 1; columns < island[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) island[row][columns];
                for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
                    List<Entity> copyList = listOfEntitiesOnPosition;
                    Entity entity = copyList.get(i);
                    if (entity.getClass().getPackageName().contains("animal")) {
                        Animal animal = (Animal) entity;
                        int saturationRatio = animal.getSaturationRatio();
                        if (saturationRatio <= 0) {
                            copyList.remove(animal);
                        }
                    }
                }
            }
        }
    }
    public void chooseAction(List<Entity> copyList, Entity animal, int row, int columns) {
        actions = enumRandomChoice.chooseRandomEnum(Actions.class);
        switch (actions) {
            case MOVE -> makeMove(copyList, animal, row, columns);
            case EAT -> eat(copyList, animal, row, columns);
            case REPRODUCE -> reproduce(copyList, animal);
            default -> throw new IllegalStateException(String.valueOf(TextMassages.FAILURE_TO_CHOOSE_ACTION));
        }
    }

    public void makeMove(List<Entity> copyList, Entity animal, int row, int columns) {
        directionOfMoving = chooseDirection();
        int travelSpeed = maxNumberOnPosition(animal);
        switch (directionOfMoving) {
            case NORTH -> moveNorth(copyList, animal, travelSpeed, row, columns);
            case SOUTH -> moveSouth(copyList, animal, travelSpeed, row, columns);
            case WEST -> moveWest(copyList, animal, travelSpeed, row, columns);
            case EAST -> moveEast(copyList, animal, travelSpeed, row, columns);
            default -> throw new IllegalStateException(TextMassages.NO_CHOOSING_DIRECTION.getMassage());
        }
        Animal movingAnimal = (Animal) animal;
        int saturationRatio = movingAnimal.getSaturationRatio();
        movingAnimal.setSaturationRatio(saturationRatio - 1);
    }

    public DirectionsOfMoving chooseDirection() {
        return enumRandomChoice.chooseRandomEnum(DirectionsOfMoving.class);
    }

    public void moveNorth(List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveNorthSouth(copyList, animal, travelSpeed, row, columns);
    }

    public void moveSouth(List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns) {
        moveNorthSouth(copyList, animal, travelSpeed, row, columns);
    }

    private void moveWest(List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveWestEast(copyList, animal, travelSpeed, row, columns);
    }

    private void moveEast(List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns) {
        moveWestEast(copyList, animal, travelSpeed, row, columns);
    }

    private void reproduce(List<Entity> copyList, Entity animal) {
        int maxNumberOnPosition = maxNumberOnPosition(animal);
        int numberOfSameAnimalOnPosition = countNumberOfSameEntityOnPosition(copyList, animal);
        if (numberOfSameAnimalOnPosition > 1 && numberOfSameAnimalOnPosition < maxNumberOnPosition) {
            changeActionDoneFlag((Animal) animal, true);
        } else {
            changeActionDoneFlag((Animal) animal, true);
            Entity newBornAnimal = entitiesProduction.createNewBornAnimal(animal);
            copyList.add(newBornAnimal);

        }
        Animal reproducingAnimal = (Animal) animal;
        int saturationRatio = reproducingAnimal.getSaturationRatio();
        reproducingAnimal.setSaturationRatio(saturationRatio - 1);
    }

    private void moveWestEast(List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns) {
        int outOfBoundArray = row + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= width) {
            changeActionDoneFlag((Animal) animal, true);
            return;
        }
        List<Entity> targetList = (List<Entity>) island[row][columns + travelSpeed];
        int maxNumberOnPosition = maxNumberOnPosition(animal);
        int countOfSameAnimals = countNumberOfSameEntityOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition) {
            copyList.remove(animal);//delete animal from current position
            changeActionDoneFlag((Animal) animal, true); // add animal to new position
            targetList.add(animal);
        }
    }

    private void changeActionDoneFlag(Animal animal, boolean flag) {

        animal.setActionDone(flag);
    }

    private void moveNorthSouth(List<Entity> copyList, Entity animal, int travelSpeed, int row, int columns) {

        int outOfBoundArray = row + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= length) {
            changeActionDoneFlag((Animal) animal, true);
            return;
        }
        List<Entity> targetList = (List<Entity>) island[row + travelSpeed][columns];
        int maxNumberOnPosition = maxNumberOnPosition(animal);
        int countOfSameAnimals = countNumberOfSameEntityOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition) {
            copyList.remove(animal);//delete animal from current position
            changeActionDoneFlag((Animal) animal, true); // mark the animal as action done
            targetList.add(animal);// add animal to new position
        }
    }

    private int maxNumberOnPosition(Entity entity) {
        int maxNumberOnPosition =0;
        String getPackageName = entity.getClass().getPackageName();
        if (!(getPackageName.contains("Grass"))) {
            Animal animalTemp = (Animal) entity;
            maxNumberOnPosition = animalTemp.getMaxNumberOnPosition();
        } else {
            Grass grass = (Grass) entity;
            maxNumberOnPosition = grass.getMaxNumberOnPosition();
        }
        return maxNumberOnPosition;
    }

    private int countNumberOfSameEntityOnPosition(List<Entity> list, Entity entity) {
        int countOfSameEntities = 0;
        if(entity != null) {
            String classNameEntity = entity.getClass().getSimpleName();
            countOfSameEntities = (int) list.stream()
                    .filter(x -> (x.getClass().getSimpleName()).equalsIgnoreCase(classNameEntity))
                    .count();
        }
        return countOfSameEntities;
    }

    private void eat(List<Entity> copyList, Entity entity, int row, int columns) {
        Entity targetEntity  = findEatableAnimalWithMaxNumberOnPosition(copyList, entity);
        int countOfTargetAnimalToEat = countNumberOfAnimalToEat(copyList, entity, targetEntity);
        for (int i = 0; i < countOfTargetAnimalToEat; i++) {
            copyList.remove(targetEntity);
        }
    }

    private int countNumberOfAnimalToEat (List<Entity> copyList, Entity entity, Entity targetEntity) {
        Animal hunterAnimal = (Animal) entity;
        double kgForFullSaturation = hunterAnimal.getKgForFullSaturation();
        double weight = 0;
        int numberOfTargetEntityOnPosition = (int) copyList.stream()
                .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(targetEntity.getClass().getSimpleName()))
                .count();
        int countOfTargetAnimalToEat = 0;
        if (ifEntityAnimal(targetEntity)) {
            Animal targetAnimal = (Animal) targetEntity;
            weight = targetAnimal.getWeight();
            int howManyFoodMayEat = (int) Math.round(kgForFullSaturation/weight*numberOfTargetEntityOnPosition);
            if (howManyFoodMayEat < 0) {
                countOfTargetAnimalToEat = numberOfTargetEntityOnPosition;
            } else {
                countOfTargetAnimalToEat = (int) Math.ceil(kgForFullSaturation/weight);
            }
        } else if (ifEntityPlant(targetEntity)){
            Grass targetFood = (Grass) targetEntity;
            weight = targetFood.getWeight();
            double howMuchEntityToEach = weight*numberOfTargetEntityOnPosition/kgForFullSaturation;
            if (howMuchEntityToEach < 0) {
                countOfTargetAnimalToEat = numberOfTargetEntityOnPosition;
            } else {
                countOfTargetAnimalToEat = (int) Math.ceil(kgForFullSaturation/weight);
            }
        }
        int minRationToEat = 2;
        if (kgForFullSaturation/(weight*countOfTargetAnimalToEat) < minRationToEat) {
            int saturationRation = hunterAnimal.getSaturationRatio();
            hunterAnimal.setSaturationRatio(saturationRation - 1);
        }
       return countOfTargetAnimalToEat;
    }
    private boolean ifEntityAnimal (Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        boolean ifEntityAnimal = packageName.contains("animal");
        return ifEntityAnimal;
    }
    private boolean ifEntityPlant (Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        boolean ifEntityAnimal = packageName.contains("plant");
        return ifEntityAnimal;
    }

    private Entity findEatableAnimalWithMaxNumberOnPosition (List<Entity> copyList, Entity entity) {
        Animal currentAnimal = (Animal) entity;
        HashMap<String, Integer> eatingRatio = currentAnimal.getEatingRatio();
        Set<String> entityEatable = eatingRatio.keySet();
        int size = entityEatable.size();
        String [] entityEatableList = entityEatable.toArray(new String[entityEatable.size()]);
        Integer[] numbersOfEatableEntity = new Integer[entityEatableList.length];
        for (int i = 0; i < entityEatableList.length; i++) {
            String nameOfEatableEntity = entityEatableList[i];
            int numberOfEatableEntityOnPosition = (int) copyList.stream()
                    .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(nameOfEatableEntity))
                    .count();
            numbersOfEatableEntity[i] = numberOfEatableEntityOnPosition;
        }
        int indexOfMax = 0;
        for (int i = 0; i < numbersOfEatableEntity.length; i++) {
            if (numbersOfEatableEntity[i] > numbersOfEatableEntity[indexOfMax]) {
                indexOfMax = i;
            }
        }
        String targetAnimalName = entityEatableList[indexOfMax];
        Entity targetEntity = entity;
        for (int i = 0; i < copyList.size(); i++) {
            if (copyList.get(i).getClass().getSimpleName().equalsIgnoreCase(targetAnimalName)) {
                targetEntity = copyList.get(i);
            }
        }

        return targetEntity;
    }

    private void setFalseIfActionDone(Object[][] island) {
        for (int row = 1; row < island.length; row++) {
            for (int columns = 1; columns < island[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) island[row][columns];
                for (int i = 0; i < listOfEntitiesOnPosition.size(); i++) {
                    Entity animal = listOfEntitiesOnPosition.get(i);
                    String packageName = animal.getClass().getPackageName();
                    if (!(packageName.contains("plant")) && animal != null) {
                        changeActionDoneFlag((Animal) animal, false);
                    }
                }
            }
        }
    }

    private void letGrassGrow (Object[][] island) {
        List<Entity> listOfEntitiesOnPosition = new ArrayList<>();
        List<Entity> newGrassList = new ArrayList<>();
        int maxNumberOfGrass = Integer.parseInt(PropertiesLoader.getGrassProperties("Grass", "maxNumberOfGrass"));
        int weight = Integer.parseInt(PropertiesLoader.getGrassProperties("Grass", "weight"));
        Grass grass = new Grass (weight, maxNumberOfGrass);

        for (int row = 1; row < island.length; row++) {
            for (int columns = 1; columns < island[row].length; columns++) {
                listOfEntitiesOnPosition = (List<Entity>) island[row][columns];
                long numberOfGrassAtEndOFDay = listOfEntitiesOnPosition.stream()
                        .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(grass.getClass().getSimpleName()))
                        .count();
                if (numberOfGrassAtEndOFDay == 0) {
                    newGrassList = grassGrowth.setGrassOnPosition();
                    listOfEntitiesOnPosition.addAll(newGrassList);
                }
                if (numberOfGrassAtEndOFDay > 0) {
                    int grassIncreasingRatio = (int) (numberOfGrassAtEndOFDay * 2);
                    for (int i = 0; i < grassIncreasingRatio; i++) {
                        listOfEntitiesOnPosition.add(new Grass(weight, maxNumberOfGrass));
                    }
                }
                newGrassList.clear();
            }
        }

    }
}






