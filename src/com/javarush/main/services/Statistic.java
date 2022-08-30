package com.javarush.main.services;

import com.javarush.main.enums.TextMessages;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Statistic {

    public void printStatisticOnStart(Object[][] island) {
        System.out.println(TextMessages.STATISTIC_ON_BEGINNING_OF_GAME.getMassage());
        List<List<Entity>> entitiesOnPositions = getEntitiesOnPosition(island);
        countNumberOfEntity(entitiesOnPositions, TextMessages.NUMBER_OF_PREDATORS, "predators");
        countNumberOfEntity(entitiesOnPositions, TextMessages.NUMBER_OF_HERBIVORES, "herbivores");
        countNumberOfEntity(entitiesOnPositions, TextMessages.NUMBER_OF_GRASS, "plant");
    }

    public void countStatisticOfDays(Object[][] island) {
        List<List<Entity>> entitiesOnPositions = getEntitiesOnPosition(island);
        countNumberOfEntity(entitiesOnPositions, TextMessages.NUMBER_OF_PREDATORS, "predators");
        countNumberOfEntity(entitiesOnPositions, TextMessages.NUMBER_OF_HERBIVORES, "herbivores");
        countNumberOfEntity(entitiesOnPositions, TextMessages.NUMBER_OF_GRASS, "plant");
    }

    private void countNumberOfEntity(List<List<Entity>> entitiesOnPositions, TextMessages textMassages, String typeOfEntity) {
        int numberOfEntity = 0;
        /*for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {*/
        for (List list : entitiesOnPositions) {
            List<Entity> entitiesOnPosition = list; //(List<Entity>) islandInstance[row][columns];
            for (Entity entity : entitiesOnPosition) {
                String packageName = entity.getClass().getPackageName();
                if (packageName.contains(typeOfEntity)) {
                    numberOfEntity++;
                }
            }

        }
        //}
        System.out.println(textMassages.getMassage() + numberOfEntity);
    }

    private List<List<Entity>> getEntitiesOnPosition (Object[][] islandInstance) {
        List<List<Entity>> entitiesOnPositions = new ArrayList<>();
        for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {
                List<Entity> entitiesPerOnePosition = (List<Entity>) islandInstance[row][columns];
                entitiesOnPositions.add(entitiesPerOnePosition);
            }
        }
        return entitiesOnPositions;
    }
}
