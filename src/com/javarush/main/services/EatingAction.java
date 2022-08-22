package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class EatingAction {
    SupportingMethods supportingMethods = new SupportingMethods();

    protected void eat(List<Entity> copyList, Animal animal, int row, int columns) {
        HashMap<String, Integer> eatingRationMap = animal.getEatingRatio();
        String targetEntity  = findRandomAnimalToEat(copyList, animal, eatingRationMap);
        boolean ifHunterCanEatTarget = ifHunterEatTarget(eatingRationMap, targetEntity);
        if (ifHunterCanEatTarget) {
            int countOfTargetAnimalToEat = numberOfFoodToEat(copyList, animal, targetEntity);
            for (int i = 0; i < copyList.size(); i++) {
                if (targetEntity.getClass().getSimpleName().equalsIgnoreCase(copyList.get(i).getClass().getSimpleName())) {
                    copyList.remove(copyList.get(i));
                    countOfTargetAnimalToEat = countOfTargetAnimalToEat - 1;
                }
                if (countOfTargetAnimalToEat == 0) {
                    i = copyList.size();
                    animal.setActionDone(true);
                }
            }
        } else {
            int saturationRation = animal.getSaturationRatio();
            animal.setSaturationRatio(saturationRation - 1);
        }
    }

    private String findRandomAnimalToEat (List<Entity> copyList, Animal animal, HashMap<String, Integer> eatingRationMap) {
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

    private boolean ifHunterEatTarget (HashMap<String, Integer> eatingRationMap, String targetEntity) {
        boolean ifHunterEatTarget = false;
        int possibilityToEatRatio = eatingRationMap.get(targetEntity);
        int randomPossibilityToEat = ThreadLocalRandom.current().nextInt(possibilityToEatRatio);
        if (randomPossibilityToEat < possibilityToEatRatio) {
            ifHunterEatTarget = true;
        }
        return ifHunterEatTarget;
    }

    private int numberOfFoodToEat(List<Entity> copyList, Animal animal, String targetEntityName) {
        Animal hunterAnimal = animal;
        int numberOfFoodToEat = 0;
        double kgForFullSaturation = hunterAnimal.getKgForFullSaturation();
        int numberOfTargetEntityOnPosition = countTotalNumberOfFoodOnPosition(copyList,animal, targetEntityName);
        Entity targetEntity = findTargetEntityOnPosition(copyList, targetEntityName);
        if (targetEntity != null) {
            numberOfFoodToEat = howMuchFoodToEat(targetEntity, numberOfTargetEntityOnPosition, kgForFullSaturation);
        }
        return numberOfFoodToEat;
    }

    private int countTotalNumberOfFoodOnPosition (List<Entity> copyList, Animal animal, String targetEntityName) {
        int numberOfTargetEntityOnPosition = 0;
        Animal hunterAnimal = animal;
        //Entity targetEntity = findTargetEntityOnPosition(copyList, targetEntityName);
        for (Entity e : copyList) {
            if(e.getClass().getSimpleName().equalsIgnoreCase(targetEntityName)) {
                numberOfTargetEntityOnPosition++;
            }
        }
        /*if (targetEntity == null) {
            return numberOfTargetEntityOnPosition;
        }*/
        return numberOfTargetEntityOnPosition;
    }

    private Entity findTargetEntityOnPosition (List<Entity> copyList, String targetEntityName) {
        Entity targetEntity = null;
        for (Entity e : copyList) {
            if(e.getClass().getSimpleName().equalsIgnoreCase(targetEntityName)) {
                targetEntity = e;
                break;
            }
        }
        return targetEntity;
    }

    private int howMuchFoodToEat (Entity targetEntity, int numberOfTargetEntityOnPosition, double kgForFullSaturation) {
        int howMuchFoodToEat = 0;
        if (supportingMethods.ifEntityAnimal(targetEntity)) {
            Animal animal = (Animal) targetEntity;
            double weightOfTargetAnimal = animal.getWeight();
            double totalWeightOfFoodOnPosition = weightOfTargetAnimal*numberOfTargetEntityOnPosition;
            if (totalWeightOfFoodOnPosition < kgForFullSaturation) {
                howMuchFoodToEat = numberOfTargetEntityOnPosition;
            } else {
                howMuchFoodToEat = (int) Math.ceil(kgForFullSaturation/weightOfTargetAnimal);
            }
        }
        if (supportingMethods.ifEntityPlant(targetEntity)) {
            Grass grass = (Grass) targetEntity;
            double grassWeight = grass.getWeight();
            int totalWeightOfFoodOnPosition = (int) (grassWeight*numberOfTargetEntityOnPosition);
            if (totalWeightOfFoodOnPosition < kgForFullSaturation) {
                howMuchFoodToEat = numberOfTargetEntityOnPosition;
            } else {
                howMuchFoodToEat = (int) Math.ceil(kgForFullSaturation/grassWeight);
            }
        }
        return howMuchFoodToEat;
    }

}
