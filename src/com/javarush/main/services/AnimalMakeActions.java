package com.javarush.main.services;

import com.javarush.main.enums.Actions;
import com.javarush.main.enums.TextMassages;
import com.javarush.main.game.Island;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.*;

public class AnimalMakeActions {
    EnumRandomChoice enumRandomChoice = new EnumRandomChoice();
    EatingAction eatingAction = new EatingAction();
    Reproduction reproduction = new Reproduction();
    SupportingMethods supportingMethods = new SupportingMethods();
    Actions actions;
    MovingAction movingAction = new MovingAction();
    GrassGrowth grassGrowth = new GrassGrowth();
    //Island island = new Island();
    Object[][] islandInstance = Island.islandInstance;

    public void islandAnimalIteration() {
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
                            chooseAction(copyListOfEntitiesOnPosition, animal, row, columns);
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
    private void chooseAction(List<Entity> copyList, Animal animal, int row, int columns) {
        actions = enumRandomChoice.chooseRandomEnum(Actions.class);
        switch (actions) {
            case MOVE -> movingAction.makeMove(copyList, animal, row, columns);
            case EAT -> eatingAction.eat(copyList, animal, row, columns);
            case REPRODUCE -> reproduction.reproduce(copyList, animal);
            default -> throw new IllegalStateException(String.valueOf(TextMassages.FAILURE_TO_CHOOSE_ACTION));
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

    /*public void makeMove(List<Entity> copyList, Animal animal, int row, int columns) {
        directionOfMoving = chooseDirection();
        int travelSpeed = animal.getMaxTravelSpeed();
        if (travelSpeed == 0) {
            return;
        }
        switch (directionOfMoving) {
            case NORTH -> moveNorth(copyList, animal, travelSpeed, row, columns);
            case SOUTH -> moveSouth(copyList, animal, travelSpeed, row, columns);
            case WEST -> moveWest(copyList, animal, travelSpeed, row, columns);
            case EAST -> moveEast(copyList, animal, travelSpeed, row, columns);
            default -> throw new IllegalStateException(TextMassages.NO_CHOOSING_DIRECTION.getMassage());
        }
        int saturationRatio = animal.getSaturationRatio();
        animal.setSaturationRatio(saturationRatio - 1);
    }*/

    /*private DirectionsOfMoving chooseDirection() {
        return enumRandomChoice.chooseRandomEnum(DirectionsOfMoving.class);
    }*/

   /* private void moveNorth(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveNorthSouth(copyList, animal, travelSpeed, row, columns);
    }

    private void moveSouth(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        moveNorthSouth(copyList, animal, travelSpeed, row, columns);
    }*/

   /* private void moveWest(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        travelSpeed = travelSpeed * -1;
        moveWestEast(copyList, animal, travelSpeed, row, columns);
    }

    private void moveEast(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        moveWestEast(copyList, animal, travelSpeed, row, columns);
    }
*/
    /*private void reproduce(List<Entity> copyList, Animal animal) {
        int maxNumberOnPosition = animal.getMaxNumberOnPosition();
        int numberOfSameAnimalOnPosition = countNumberOfSameEntityOnPosition(copyList, animal);
        if (numberOfSameAnimalOnPosition > 1 && numberOfSameAnimalOnPosition < maxNumberOnPosition) {
            changeActionDoneFlag(animal, true);
            Entity newBornAnimal = entitiesProduction.createNewBornAnimal(animal);
            copyList.add(newBornAnimal);
        } else {
            changeActionDoneFlag(animal, true);
        }
        int saturationRatio = animal.getSaturationRatio();
        animal.setSaturationRatio(saturationRatio - 1);
    }*/

    /*private void moveWestEast(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {
        int outOfBoundArray = columns + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= width) {
            changeActionDoneFlag(animal, true);
            return;
        }
        List<Entity> targetList = (List<Entity>) island[row][columns + travelSpeed];
        int maxNumberOnPosition = maxNumberOnPosition(animal);
        int countOfSameAnimals = countNumberOfSameEntityOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition) {
            copyList.remove(animal);
            changeActionDoneFlag(animal, true);
            targetList.add(animal);
        }
    }*/

   /* private void changeActionDoneFlag(Animal animal, boolean flag) {

        animal.setActionDone(flag);
    }*/

    /*private void moveNorthSouth(List<Entity> copyList, Animal animal, int travelSpeed, int row, int columns) {

        int outOfBoundArray = row + travelSpeed;
        if (outOfBoundArray <= 0 || outOfBoundArray >= length) {
            changeActionDoneFlag(animal, true);
            return;
        }
        List<Entity> targetList = (List<Entity>) island[row + travelSpeed][columns];
        int maxNumberOnPosition = animal.getMaxNumberOnPosition();
        int countOfSameAnimals = countNumberOfSameEntityOnPosition(targetList, animal);
        if (countOfSameAnimals < maxNumberOnPosition) {
            copyList.remove(animal);//delete animal from current position
            changeActionDoneFlag(animal, true); // mark the animal as action done
            targetList.add(animal);// add animal to new position
        }
    }*/

    /*private int maxNumberOnPosition(Entity entity) {
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
    }*/

    /*private int countNumberOfSameEntityOnPosition(List<Entity> list, Entity entity) {
        int countOfSameEntities = 0;
        if(entity != null) {
            String classNameEntity = entity.getClass().getSimpleName();
            countOfSameEntities = (int) list.stream()
                    .filter(x -> (x.getClass().getSimpleName()).equalsIgnoreCase(classNameEntity))
                    .count();
        }
        return countOfSameEntities;
    }
*/
    /*private void eat(List<Entity> copyList, Animal animal, int row, int columns) {
        HashMap<String, Integer> eatingRationMap = animal.getEatingRatio();
        String targetEntity  = findRandomAnimalToEat(copyList, animal, eatingRationMap);
        boolean ifHunterCanEatTarget = ifHunterEatTarget(eatingRationMap, targetEntity);
        if (ifHunterCanEatTarget) {
            int countOfTargetAnimalToEat = countNumberOfAnimalToEat(copyList, animal, targetEntity);
            for (int i = 0; i < copyList.size(); i++) {
                if (targetEntity.getClass().getSimpleName().equalsIgnoreCase(copyList.get(i).getClass().getSimpleName()))
                copyList.remove(targetEntity);
                countOfTargetAnimalToEat = countOfTargetAnimalToEat - 1;
                if (countOfTargetAnimalToEat == 0 ){
                    continue;
                }
            }
        }
        animal.setActionDone(true);
    }*/
    /*public boolean ifHunterEatTarget (HashMap<String, Integer> eatingRationMap, String targetEntity) {
        boolean ifHunterEatTarget = false;
        int possibilityToEatRatio = eatingRationMap.get(targetEntity);
        int randomPossibilityToEat = ThreadLocalRandom.current().nextInt(possibilityToEatRatio);
        if (randomPossibilityToEat < possibilityToEatRatio) {
            ifHunterEatTarget = true;
        }
        return ifHunterEatTarget;
    }*/

    /*private int countNumberOfAnimalToEat (List<Entity> copyList, Animal animal, String targetEntityName) {
        int howMuchEntityToEat = 0;
        Animal hunterAnimal = animal;
        double kgForFullSaturation = hunterAnimal.getKgForFullSaturation();
        double weight = 0;
        int numberOfTargetEntityOnPosition = 0;
        Entity targetEntity = entity;
        for (Entity e : copyList) {
            if(e.getClass().getSimpleName().equalsIgnoreCase(targetEntityName)) {
                numberOfTargetEntityOnPosition++;
                targetEntity = e;
            }

        }
        if (targetEntity == null) {
            return numberOfTargetEntityOnPosition;
        }
        if (ifEntityAnimal(targetEntity)) {
            Animal targetAnimal = (Animal) targetEntity;
            weight = targetAnimal.getWeight();
            int totalWeightOfFoodOnPosition = (int) (weight*numberOfTargetEntityOnPosition);
            if (totalWeightOfFoodOnPosition < kgForFullSaturation) {
                howMuchEntityToEat = numberOfTargetEntityOnPosition;
            } else {
                howMuchEntityToEat = (int) Math.ceil(kgForFullSaturation/weight);
            }
        } else if (ifEntityPlant(targetEntity)){
            Grass targetFood = (Grass) targetEntity;
            weight = targetFood.getWeight();
            int totalWeightOfFoodOnPosition = (int) (weight*numberOfTargetEntityOnPosition);
            if (totalWeightOfFoodOnPosition < kgForFullSaturation) {
                howMuchEntityToEat = numberOfTargetEntityOnPosition;
            } else {
                howMuchEntityToEat = (int) Math.ceil(kgForFullSaturation/weight);
            }
        }
        int minRationToEat = 2;
        if (kgForFullSaturation/(weight*howMuchEntityToEat) > minRationToEat) {
            int saturationRation = hunterAnimal.getSaturationRatio();
            hunterAnimal.setSaturationRatio(saturationRation - 1);
        }
       return howMuchEntityToEat;
    }*/
   /* private boolean ifEntityAnimal (Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        boolean ifEntityAnimal = packageName.contains("animal");
        return ifEntityAnimal;
    }
    private boolean ifEntityPlant (Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        boolean ifEntityAnimal = packageName.contains("plant");
        return ifEntityAnimal;
    }
*/
   /* private String findRandomAnimalToEat (List<Entity> copyList, Animal animal, HashMap<String, Integer> eatingRationMap) {
        Set<String> entityEatable = eatingRationMap.keySet();
        int size = entityEatable.size();
        int randomPossibilityToEat = 0;
        String [] entityEatableList = entityEatable.toArray(new String[entityEatable.size()]);
        if (size > 1) {
            randomPossibilityToEat = ThreadLocalRandom.current().nextInt(entityEatableList.length);
        }
        String targetAnimalName = entityEatableList[randomPossibilityToEat];
        return targetAnimalName;
    }
*/

}






