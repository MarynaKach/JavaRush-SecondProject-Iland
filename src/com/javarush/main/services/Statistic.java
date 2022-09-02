package com.javarush.main.services;

import com.javarush.main.enums.AnimalEnum;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.ArrayList;
import java.util.List;

public class Statistic {

    public void countStatisticOfDays(Object[][] islandInstance) {
        List<List<Entity>> entitiesOnPositions = new ArrayList<>();
        for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {
                List<Entity> entitiesPerOnePosition = (List<Entity>) islandInstance[row][columns];
                entitiesOnPositions.add(entitiesPerOnePosition);
            }
        }
        countNumberOfEachAnimal(entitiesOnPositions);
    }

    private void countNumberOfEachAnimal(List<List<Entity>> entitiesOnPositions) {
        int numberOfAnimal = 0;
        for(AnimalEnum animal : AnimalEnum.values()) {
            for (List list : entitiesOnPositions) {
            long numberOfAnimalOnCurrentPosition = list.stream()
                        .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(animal.getName()))
                        .count();
            numberOfAnimal = (int) (numberOfAnimal + numberOfAnimalOnCurrentPosition);
            }
            System.out.println(animal.getName() + " = " + numberOfAnimal);
            numberOfAnimal = 0;
        }
    }
}
