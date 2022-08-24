package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.List;

public class SupportingMethods {

    protected void changeActionDoneFlag(Animal animal, boolean flag) {
        animal.setActionDone(flag);
    }

    protected int maxNumberOnPosition(Entity entity) {
        int maxNumberOnPosition = 0;
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

    protected int countNumberOfSameEntityOnPosition(List<Entity> list, Entity entity) {
        int countOfSameEntities = 0;
        if (entity != null) {
            String classNameEntity = entity.getClass().getSimpleName();
            countOfSameEntities = (int) list.stream()
                    .filter(x -> (x.getClass().getSimpleName()).equalsIgnoreCase(classNameEntity))
                    .count();
        }
        return countOfSameEntities;
    }

    protected boolean ifEntityAnimal(Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        boolean ifEntityAnimal = !(packageName.contains("plant"));
        return ifEntityAnimal;
    }

    protected boolean ifEntityPlant(Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        boolean ifEntityAnimal = packageName.contains("plant");
        return ifEntityAnimal;
    }
}
