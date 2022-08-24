package com.javarush.main.game;

import com.javarush.main.consoleui.ScannerSingleton;
import com.javarush.main.enums.AnimalParametersTypes;
import com.javarush.main.enums.TextMessages;
import com.javarush.main.services.PropertiesLoader;

import java.util.List;
import java.util.regex.Pattern;

public class IslandInitialization {
    private Island island;
    int minLimitWidth = Integer.parseInt(PropertiesLoader
            .properties.getProperty("Island_" + AnimalParametersTypes.MIN_LIMIT_WIDTH.getName()));
    int minLimitLength = Integer.parseInt(PropertiesLoader
            .properties.getProperty("Island_" + AnimalParametersTypes.MIN_LIMIT_LENGTH.getName()));
    int newIslandLandWidth = 0;
    int newIslandLandLength = 0;
    int defaultIslandWidth = Integer.parseInt(PropertiesLoader
            .properties.getProperty("Island_" + AnimalParametersTypes.WIDTH.getName()));
    int defaultIslandLength = Integer.parseInt(PropertiesLoader
            .properties.getProperty("Island_" + AnimalParametersTypes.LENGTH.getName()));

    double plantGrowthRatio = Double.parseDouble(PropertiesLoader
            .properties.getProperty("Island_" + AnimalParametersTypes.PLANT_GROWTH_RATIO.getName()));

    public int getDefaultIslandWidth() {
        return defaultIslandWidth;
    }

    public int getDefaultIslandLength() {
        return defaultIslandLength;
    }

    public int getNewIslandLandWidth() {
        return newIslandLandWidth;
    }

    public int getNewIslandLandLength() {
        return newIslandLandLength;
    }

    public double getPlantGrowthRatio() {
        return plantGrowthRatio;
    }

    protected Island createIsland(int width, int length) {
        int daysGameLasts = Integer.parseInt(PropertiesLoader
                .properties.getProperty("Island_" + AnimalParametersTypes.DAYS_GAME_LAST.getName()));
        int minLimitWidth = Integer.parseInt(PropertiesLoader
                .properties.getProperty("Island_" + AnimalParametersTypes.MIN_LIMIT_WIDTH.getName()));
        int minLimitLength = Integer.parseInt(PropertiesLoader
                .properties.getProperty("Island_" + AnimalParametersTypes.MIN_LIMIT_LENGTH.getName()));
        /*double plantGrowthRatio = Double.parseDouble(PropertiesLoader
                .properties.getProperty("Island_" + AnimalParametersTypes.PLANT_GROWTH_RATIO.getName()));*/
        List[][] islandInstance = new List[length][width];
        island = new Island(length, width, daysGameLasts, minLimitWidth, minLimitLength, plantGrowthRatio, islandInstance);
        return island;
    }

    protected void chooseNewIslandSize() {
        newIslandLandWidth = typeNewIslandSize(TextMessages.TYPE_NEW_WIDTH, minLimitWidth);
        newIslandLandLength = typeNewIslandSize(TextMessages.TYPE_NEW_LENGTH, minLimitLength);
        System.out.printf(TextMessages.NEW_SIZE_OF_ISLAND.getMassage(), newIslandLandWidth, newIslandLandLength);
    }

    private int typeNewIslandSize(TextMessages textMassages, int lowLimitSize) {
        System.out.printf(textMassages.getMassage(), lowLimitSize);
        String newSize = ScannerSingleton.getInstance().nextLine();
        while (!Pattern.matches("[0-9]+", newSize) || Integer.parseInt(newSize) < lowLimitSize) {
            System.out.printf(textMassages.getMassage(), lowLimitSize);
            newSize = ScannerSingleton.getInstance().nextLine();
        }
        return Integer.parseInt(newSize);
    }
}
