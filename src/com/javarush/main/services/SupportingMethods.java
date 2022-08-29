package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Phaser;

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
        List<Entity> list1 = list;
        int countOfSameEntities = 0;
        if (entity != null) {
            String classNameEntity = entity.getClass().getSimpleName();
            countOfSameEntities = (int) list1.stream()
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

    public List<Runnable> getListOfTasksFirst(Phaser phaser, Object[][] islandInstance) {
        List<Runnable> listOfTasks = new ArrayList<>();
        for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {
                listOfTasks.add(new IslandEntityIterationRunnable(phaser, islandInstance, row, columns));
            }
        }
        return listOfTasks;
    }
}
