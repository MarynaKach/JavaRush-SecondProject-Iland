package com.javarush.main.services;

import com.javarush.main.enums.TextMassages;
import java.util.List;

public class Statistic {
    public void printTotalStatistic (Object[][] island) {
        System.out.println(TextMassages.STATISTIC_ON_BEGINNING_OF_GAME.getMassage());
        countNumberOfPredators(island);
        countNumberOfHerbivores(island);
        countNumberOfGrass(island);
    }

    public void countNumberOfPredators(Object[][] initializedIsland) {
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

    public void countNumberOfHerbivores(Object[][] initializedIsland) {
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
    }

    public void countNumberOfGrass(Object[][] initializedIsland) {
        int numberOfGrass = 0;
        for (int row = 0; row < initializedIsland.length; row++) {
            for (int columns = 0; columns < initializedIsland[row].length; columns++) {
                if (initializedIsland[row][columns] != null) {
                    List<Object> list = (List<Object>) initializedIsland[row][columns];
                    for (int i = 0; i < list.size(); i++) {
                        String packageName = list.get(i).getClass().getPackageName();
                        if (packageName.contains("plant")) {
                            numberOfGrass++;
                        }
                    }
                }
            }

        }
        System.out.println(TextMassages.NUMBER_OF_GRASS.getMassage() + numberOfGrass);
    }
}
