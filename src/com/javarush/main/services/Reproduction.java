package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.concurrent.CopyOnWriteArrayList;

public class Reproduction {
    EntitiesProduction entitiesProduction = new EntitiesProduction();

    public void reproduce(CopyOnWriteArrayList<Entity> entitiesOnPosition, Animal animal) {
        int maxNumberOnPosition = animal.getMaxNumberOnPosition();
        int numberOfSameAnimalOnPosition = countNumberOfSameEntityOnPosition(entitiesOnPosition, animal);
        if (numberOfSameAnimalOnPosition > 1 && numberOfSameAnimalOnPosition < maxNumberOnPosition) {
            changeActionDoneFlag(animal, true);
            Entity newBornAnimal = entitiesProduction.createNewBornAnimal(animal);
            entitiesOnPosition.add(newBornAnimal);
        } else {
            changeActionDoneFlag(animal, true);
        }
        int saturationRatio = animal.getSaturationRatio();
        animal.setSaturationRatio(saturationRatio - 1);
    }

    private int countNumberOfSameEntityOnPosition(CopyOnWriteArrayList<Entity> entitiesOnPosition, Entity entity) {
        int countOfSameEntities = 0;

            if (entity != null) {
                String classNameEntity = entity.getClass().getSimpleName();
                countOfSameEntities = (int) entitiesOnPosition.stream()
                        .filter(x -> (x.getClass().getSimpleName()).equalsIgnoreCase(classNameEntity))
                        .count();
            }

        return countOfSameEntities;
    }

    private void changeActionDoneFlag(Animal animal, boolean flag) {
        animal.setActionDone(flag);
    }
}
