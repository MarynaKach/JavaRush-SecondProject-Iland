package com.javarush.main.services;

import com.javarush.main.enums.TextMessages;

import java.util.List;

public class Statistic {

    public void printStatisticOnStart(Object[][] island) {
        System.out.println(TextMessages.STATISTIC_ON_BEGINNING_OF_GAME.getMassage());
        countNumberOfEntity(island, TextMessages.NUMBER_OF_PREDATORS, "predators");
        countNumberOfEntity(island, TextMessages.NUMBER_OF_HERBIVORES, "herbivores");
        countNumberOfEntity(island, TextMessages.NUMBER_OF_GRASS, "plant");
    }

    public void countStatisticOfDays(Object[][] island) {
        countNumberOfEntity(island, TextMessages.NUMBER_OF_PREDATORS, "predators");
        countNumberOfEntity(island, TextMessages.NUMBER_OF_HERBIVORES, "herbivores");
        countNumberOfEntity(island, TextMessages.NUMBER_OF_GRASS, "plant");
    }

    private void countNumberOfEntity(Object[][] initializedIsland, TextMessages textMassages, String typeOfEntity) {
        int numberOfEntity = 0;
        for (int row = 0; row < initializedIsland.length; row++) {
            for (int columns = 0; columns < initializedIsland[row].length; columns++) {
                if (initializedIsland[row][columns] != null) {
                    List<Object> list = (List<Object>) initializedIsland[row][columns];
                    for (int i = 0; i < list.size(); i++) {
                        String packageName = list.get(i).getClass().getPackageName();
                        if (packageName.contains(typeOfEntity)) {
                            numberOfEntity++;
                        }
                    }
                }
            }
        }
        System.out.println(textMassages.getMassage() + numberOfEntity);
    }
}
