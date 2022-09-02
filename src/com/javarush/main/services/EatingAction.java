package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EatingAction {
    SupportingMethods supportingMethods = new SupportingMethods();

    protected void eat(CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal) {
        HashMap<String, Integer> eatingRationMap = animal.getEatingRatio();
        String targetEntity = findFoodRandomly(eatingRationMap);
        boolean ifHunterCanEatTarget = ifHunterEatTarget(eatingRationMap, targetEntity);
        int numberOfTargetAnimalToEat = numberOfTargetFoodToEat(entitiesOnPosition, animal, targetEntity);
        if (ifHunterCanEatTarget) {
            for (int i = 0; i < entitiesOnPosition.size(); i++) {
                Entity currentEntityOnPosition = entitiesOnPosition.get(i);
                if (findTargetEntityOnPosition(targetEntity, currentEntityOnPosition)) {
                    entitiesOnPosition.remove(currentEntityOnPosition);
                    numberOfTargetAnimalToEat = numberOfTargetAnimalToEat - 1;
                    i--;
                }
                if (numberOfTargetAnimalToEat == 0) {
                    i = entitiesOnPosition.size();
                }
            }
        } else {
            int saturationRation = animal.getSaturationRatio();
            animal.setSaturationRatio(saturationRation - 1);
        }
        checkIfAnimalEatEnough(entitiesOnPosition, targetEntity, numberOfTargetAnimalToEat,animal);
        animal.setActionDone(true);
    }



    private String findFoodRandomly(HashMap<String, Integer> eatingRationMap) {
        Set<String> entityEatable = eatingRationMap.keySet();
        int size = entityEatable.size();
        int randomPossibilityToEat = 0;
        String[] entityEatableList = entityEatable.toArray(new String[entityEatable.size()]);
        if (size > 1) {
            randomPossibilityToEat = ThreadLocalRandom.current().nextInt(entityEatableList.length);
        }
        return entityEatableList[randomPossibilityToEat];
    }

    private boolean ifHunterEatTarget(HashMap<String, Integer> eatingRationMap, String targetEntity) {
        int possibilityToEatRatio = eatingRationMap.get(targetEntity);
        int randomPossibilityToEat = ThreadLocalRandom.current().nextInt(possibilityToEatRatio);
        return randomPossibilityToEat < possibilityToEatRatio;
    }

    private int numberOfTargetFoodToEat(CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal, String targetEntityName) {
        int numberOfTargetFoodToEat = 0;
        double kgForFullSaturation = animal.getKgForFullSaturation();
        int numberOfTargetEntityOnPosition = countTotalNumberOfTargetFoodOnPosition(entitiesOnPosition, targetEntityName);
        Entity targetEntity = findTargetEntityOnPosition(entitiesOnPosition, targetEntityName);
        if (targetEntity != null) {
            numberOfTargetFoodToEat = getNumberOfTargetFoodToEat(targetEntity, numberOfTargetEntityOnPosition, kgForFullSaturation);
        }
        return numberOfTargetFoodToEat;
    }

    private boolean findTargetEntityOnPosition(String targetEntity, Entity currentEntityOnPosition) {
        return targetEntity.equalsIgnoreCase(currentEntityOnPosition.getClass().getSimpleName());
    }

    private int countTotalNumberOfTargetFoodOnPosition(CopyOnWriteArrayList<Entity> entitiesOnPosition, String targetEntityName) {
        int numberOfTargetEntityOnPosition = 0;
        for (Entity e : entitiesOnPosition) {
            if (e.getClass().getSimpleName().equalsIgnoreCase(targetEntityName)) {
                numberOfTargetEntityOnPosition++;
            }
        }
        return numberOfTargetEntityOnPosition;
    }

    private Entity findTargetEntityOnPosition(CopyOnWriteArrayList<Entity> entitiesOnPosition, String targetEntityName) {
        Entity targetEntity = null;
        for (Entity e : entitiesOnPosition) {
            if (e.getClass().getSimpleName().equalsIgnoreCase(targetEntityName)) {
                targetEntity = e;
                break;
            }
        }
        return targetEntity;
    }

    private int getNumberOfTargetFoodToEat(Entity targetEntity, int numberOfTargetEntityOnPosition, double kgForFullSaturation) {
        int howMuchFoodToEat = 0;
        if (supportingMethods.ifEntityAnimal(targetEntity)) {
            Animal animal = (Animal) targetEntity;
            double weightOfTargetAnimal = animal.getWeight();
            double totalWeightOfFoodOnPosition = weightOfTargetAnimal * numberOfTargetEntityOnPosition;
            if (totalWeightOfFoodOnPosition < kgForFullSaturation) {
                howMuchFoodToEat = numberOfTargetEntityOnPosition;
            } else {
                howMuchFoodToEat = (int) Math.ceil(kgForFullSaturation / weightOfTargetAnimal);
            }
        }
        if (supportingMethods.ifEntityPlant(targetEntity)) {
            Grass grass = (Grass) targetEntity;
            double grassWeight = grass.getWeight();
            int totalWeightOfFoodOnPosition = (int) (grassWeight * numberOfTargetEntityOnPosition);
            if (totalWeightOfFoodOnPosition < kgForFullSaturation) {
                howMuchFoodToEat = numberOfTargetEntityOnPosition;
            } else {
                howMuchFoodToEat = (int) Math.ceil(kgForFullSaturation / grassWeight);
            }
        }
        return howMuchFoodToEat;
    }

    private double getWeightOfFoodEaten (CopyOnWriteArrayList<Entity> entitiesOnPosition, String targetEntity, int numberOfTargetAnimalToEat) {
        Entity entity = findTargetEntityOnPosition(entitiesOnPosition, targetEntity);
        double weightOfFoodEaten = 0;
        if (entity != null) {
            if (supportingMethods.ifEntityPlant(entity)) {
                Grass grass = (Grass) entity;
                weightOfFoodEaten = numberOfTargetAnimalToEat * grass.getWeight();
            }
            if (supportingMethods.ifEntityAnimal(entity)) {
                Animal animal = (Animal) entity;
                weightOfFoodEaten = (numberOfTargetAnimalToEat * animal.getWeight());
            }
        }
        return weightOfFoodEaten;
    }

    private void checkIfAnimalEatEnough(CopyOnWriteArrayList<Entity> entitiesOnPosition, String targetEntity, int numberOfTargetAnimalToEat, Animal animal) {
        double weightOfFoodEaten = getWeightOfFoodEaten(entitiesOnPosition, targetEntity, numberOfTargetAnimalToEat);
        double hungrinessRatio = 1.6;
        double animalHungrinessRation = animal.getKgForFullSaturation()/weightOfFoodEaten;
        if (animalHungrinessRation > hungrinessRatio) {
            int saturationRation = animal.getSaturationRatio();
            animal.setSaturationRatio(saturationRation - 1);
        }
    }
}
