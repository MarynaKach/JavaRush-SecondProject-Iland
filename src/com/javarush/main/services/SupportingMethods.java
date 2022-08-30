package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Phaser;

public class SupportingMethods {

    protected void changeActionDoneFlag(Animal animal, boolean flag) {
        animal.setActionDone(flag);
    }

    protected int maxNumberOnPosition(Entity entity) {
        int maxNumberOnPosition;
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

    protected int countNumberOfSameEntityOnPosition(CopyOnWriteArrayList<Entity> entitiesOnPosition, Entity entity) {
        int countOfSameEntities = 0;
        if (entity != null) {
            String classNameEntity = entity.getClass().getSimpleName();
            countOfSameEntities = (int) entitiesOnPosition.stream()
                    .filter(x -> (x.getClass().getSimpleName()).equalsIgnoreCase(classNameEntity))
                    .count();
        }
        return countOfSameEntities;
    }

    protected boolean ifEntityAnimal(Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        return !(packageName.contains("plant"));
    }

    protected boolean ifEntityPlant(Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        return packageName.contains("plant");
    }

    public List<Runnable> getThreadTaskPerPosition(Phaser phaser, Object[][] islandInstance) {
        List<Runnable> listOfTasks = new ArrayList<>();
        for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {
                listOfTasks.add(new IslandEntityIterationRunnable(phaser, islandInstance, row, columns));
            }
        }
        return listOfTasks;
    }
}
