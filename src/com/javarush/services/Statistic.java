package com.javarush.services;

import com.javarush.enums.TextMassages;
import com.javarush.game.IslandInitialization;
import java.util.List;

public class Statistic {
    IslandInitialization islandInitialization = new IslandInitialization();
    int length = islandInitialization.getLength();
    int width = islandInitialization.getWidth();

    public void printTotalStatistic (Object[][] objects) {
        countNumberOfPredators(objects);
        countNumberOfHerbivores(objects);
        countNumberOfGrass(objects);
    }

    public void countNumberOfPredators(Object[][] objects) {
        int numberOfPredators = 0;
        for (int row = 0; row < length; row++) {
            for (int columns = 0; columns < width; columns++) {
                if (objects[row][columns] != null) {
                    List<Object> list = (List<Object>) objects[row][columns];
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

    public void countNumberOfHerbivores(Object[][] objects) {
        int numberOfHerbivores = 0;
        for (int row = 0; row < length; row++) {
            for (int columns = 0; columns < width; columns++) {
                if (objects[row][columns] != null) {
                    List<Object> list = (List<Object>) objects[row][columns];
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

    public void countNumberOfGrass(Object[][] objects) {
        int numberOfGrass = 0;
        for (int row = 0; row < length; row++) {
            for (int columns = 0; columns < width; columns++) {
                if (objects[row][columns] != null) {
                    List<Object> list = (List<Object>) objects[row][columns];
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
