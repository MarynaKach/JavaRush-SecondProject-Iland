package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.List;

public class Reproduction {
    EntitiesProduction entitiesProduction = new EntitiesProduction();

    public void reproduce(List<Entity> copyList, Animal animal) {
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
    }

    private int countNumberOfSameEntityOnPosition(List<Entity> list, Entity entity) {
        int countOfSameEntities = 0;
        List<Entity> list1 = list;
        synchronized (list1) {
            if (entity != null) {
                String classNameEntity = entity.getClass().getSimpleName();
                countOfSameEntities = (int) list1.stream()
                        .filter(x -> (x.getClass().getSimpleName()).equalsIgnoreCase(classNameEntity))
                        .count();
            }
        }
        return countOfSameEntities;
    }

    private void changeActionDoneFlag(Animal animal, boolean flag) {
        animal.setActionDone(flag);
    }
}
