package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.List;

public class SupportingMethods {
    protected void killingOfHungryAnimal(Object[][] island) {
        for (int row = 1; row < island.length; row++) {
            for (int columns = 1; columns < island[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) island[row][columns];
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

    protected void changeActionDoneFlag(Animal animal, boolean flag) {

        animal.setActionDone(flag);
    }
    protected int maxNumberOnPosition(Entity entity) {
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
    protected int countNumberOfSameEntityOnPosition(List<Entity> list, Entity entity) {
        int countOfSameEntities = 0;
        if(entity != null) {
            String classNameEntity = entity.getClass().getSimpleName();
            countOfSameEntities = (int) list.stream()
                    .filter(x -> (x.getClass().getSimpleName()).equalsIgnoreCase(classNameEntity))
                    .count();
        }
        return countOfSameEntities;
    }
    protected boolean ifEntityAnimal (Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        boolean ifEntityAnimal = !(packageName.contains("plant"));
        return ifEntityAnimal;
    }
    protected boolean ifEntityPlant (Entity targetEntity) {
        String packageName = targetEntity.getClass().getPackageName();
        boolean ifEntityAnimal = packageName.contains("plant");
        return ifEntityAnimal;
    }
    protected void setFalseIfActionDone(Object[][] island) {
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

}
