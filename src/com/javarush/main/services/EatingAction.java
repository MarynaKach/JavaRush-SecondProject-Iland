package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class EatingAction {
    SupportingMethods supportingMethods = new SupportingMethods();

    protected void eat(CopyOnWriteArrayList<Entity> copyList, Animal animal) {
        HashMap<String, Integer> eatingRationMap = animal.getEatingRatio();
        String targetEntity = findFoodRandomly(eatingRationMap);
        boolean ifHunterCanEatTarget = ifHunterEatTarget(eatingRationMap, targetEntity);
        if (ifHunterCanEatTarget) {
            int numberOfTargetAnimalToEat = numberOfTargetFoodToEat(copyList, animal, targetEntity);
            for (int i = 0; i < copyList.size(); i++) {
                CopyOnWriteArrayList<Entity> copyOfCopyList = copyList;
                Entity currentEntityOnPosition = copyOfCopyList.get(i);
                if (findTargetEntityOnPosition(targetEntity, currentEntityOnPosition)) {
                    copyList.remove(currentEntityOnPosition);
                    numberOfTargetAnimalToEat = numberOfTargetAnimalToEat - 1;
                    i--;
                }
                if (numberOfTargetAnimalToEat == 0) {
                    i = copyList.size();
                    animal.setActionDone(true);
                }
                copyList = copyOfCopyList;
            }
        } else {
            int saturationRation = animal.getSaturationRatio();
            animal.setSaturationRatio(saturationRation - 1);
        }
    }

    private String findFoodRandomly(HashMap<String, Integer> eatingRationMap) {
        Set<String> entityEatable = eatingRationMap.keySet();
        int size = entityEatable.size();
        int randomPossibilityToEat = 0;
        String[] entityEatableList = entityEatable.toArray(new String[entityEatable.size()]);
        if (size > 1) {
            randomPossibilityToEat = ThreadLocalRandom.current().nextInt(entityEatableList.length);
        }
        String targetAnimalName = entityEatableList[randomPossibilityToEat];
        return targetAnimalName;
    }

    private boolean ifHunterEatTarget(HashMap<String, Integer> eatingRationMap, String targetEntity) {
        int possibilityToEatRatio = eatingRationMap.get(targetEntity);
        int randomPossibilityToEat = ThreadLocalRandom.current().nextInt(possibilityToEatRatio);
        return randomPossibilityToEat < possibilityToEatRatio;
    }

    private int numberOfTargetFoodToEat(List<Entity> copyList, Animal animal, String targetEntityName) {
        Animal hunterAnimal = animal;
        int numberOfTargetFoodToEat = 0;
        double kgForFullSaturation = hunterAnimal.getKgForFullSaturation();
        int numberOfTargetEntityOnPosition = countTotalNumberOfTargetFoodOnPosition(copyList, targetEntityName);
        Entity targetEntity = findTargetEntityOnPosition(copyList, targetEntityName);
        if (targetEntity != null) {
            numberOfTargetFoodToEat = howMuchFoodToEat(targetEntity, numberOfTargetEntityOnPosition, kgForFullSaturation);
        }
        return numberOfTargetFoodToEat;
    }

    private boolean findTargetEntityOnPosition(String targetEntity, Entity currentEntityOnPosition) {
        return targetEntity.equalsIgnoreCase(currentEntityOnPosition.getClass().getSimpleName());
    }

    private int countTotalNumberOfTargetFoodOnPosition(List<Entity> copyList, String targetEntityName) {
        int numberOfTargetEntityOnPosition = 0;
        for (Entity e : copyList) {
            if (e.getClass().getSimpleName().equalsIgnoreCase(targetEntityName)) {
                numberOfTargetEntityOnPosition++;
            }
        }
        return numberOfTargetEntityOnPosition;
    }

    private Entity findTargetEntityOnPosition(List<Entity> copyList, String targetEntityName) {
        Entity targetEntity = null;
        for (Entity e : copyList) {
            if (e.getClass().getSimpleName().equalsIgnoreCase(targetEntityName)) {
                targetEntity = e;
                break;
            }
        }
        return targetEntity;
    }

    private int howMuchFoodToEat(Entity targetEntity, int numberOfTargetEntityOnPosition, double kgForFullSaturation) {
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
}
