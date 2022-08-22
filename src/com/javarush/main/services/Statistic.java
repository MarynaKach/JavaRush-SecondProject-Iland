package com.javarush.main.services;

import com.javarush.main.enums.TextMassages;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.List;

public class Statistic {
    public void printStatisticOnStart (List<List<List<Entity>>> islandInstance) {
        System.out.println(TextMassages.STATISTIC_ON_BEGINNING_OF_GAME.getMassage());
        countNumberOfEntity(islandInstance, "predators", TextMassages.NUMBER_OF_PREDATORS);
        countNumberOfEntity(islandInstance, "herbivores", TextMassages.NUMBER_OF_HERBIVORES);
        countNumberOfEntity(islandInstance, "plant", TextMassages.NUMBER_OF_GRASS);
    }
    public void countStatisticOfDays (List<List<List<Entity>>> islandInstance) {
        countNumberOfEntity(islandInstance, "predators", TextMassages.NUMBER_OF_PREDATORS);
        countNumberOfEntity(islandInstance, "herbivores", TextMassages.NUMBER_OF_HERBIVORES);
        countNumberOfEntity(islandInstance, "plant", TextMassages.NUMBER_OF_GRASS);
    }

    /*public void countNumberOfPredators(List<List<List<Entity>>> islandInstance) {
        int numberOfPredators = 0;
        for (int row = 0; row < initializedIsland.length; row++) {
            for (int columns = 0; columns < initializedIsland[row].length; columns++) {
                if (initializedIsland[row][columns] != null) {
                    List<Object> list = (List<Object>) initializedIsland[row][columns];
                    for (int i = 0; i < list.size(); i++) {
                        String packageName = list.get(i).getClass().getPackageName();
                        if(packageName.contains("predators")) {
                            numberOfPredators++;
                        }
                    }
                }
            }
        }
        System.out.println(TextMassages.NUMBER_OF_PREDATORS.getMassage() + numberOfPredators);

    }
*/
   /* public void countNumberOfHerbivores(List<List<List<Entity>>> islandInstance) {
        int numberOfHerbivores = 0;
        for (int row = 0; row < initializedIsland.length; row++) {
            for (int columns = 0; columns < initializedIsland[row].length; columns++) {
                if (initializedIsland[row][columns] != null) {
                    List<Object> list = (List<Object>) initializedIsland[row][columns];
                    for (int i = 0; i < list.size(); i++) {
                        String packageName = list.get(i).getClass().getPackageName();
                        if(packageName.contains("herbivores")) {
                            numberOfHerbivores++;
                        }
                    }
                }
            }
        }
        System.out.println(TextMassages.NUMBER_OF_HERBIVORES.getMassage() + numberOfHerbivores);
    }*/

    public void countNumberOfEntity(List<List<List<Entity>>> islandInstance, String entity, TextMassages textMassages) {
        int numberOfEntity = 0;
        for (int row = 0; row < islandInstance.size(); row++) {
            for (int columns = 0; columns < islandInstance.get(row).size(); columns++) {
                if (islandInstance.get(row).get(columns) != null) {
                    List<Entity> list = islandInstance.get(row).get(columns);
                    numberOfEntity = (int) list.stream()
                            .filter(x -> x.getClass().getPackageName().contains(entity))
                            .count();
                    /*for (int i = 0; i < list.size(); i++) {
                        String packageName = list.get(i).getClass().getPackageName();
                        if (packageName.contains("plant")) {
                            numberOfGrass++;
                        }
                    }*/
                }
            }

        }
        System.out.println(textMassages.getMassage() + numberOfEntity);
    }
}
