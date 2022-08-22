package com.javarush.main.game;

public class Island {
    public static int length = 100;
    public static int width = 15;
    public static int daysGameLasts = 5;
    public static int minLimitWidth = 3;
    public static int minLimitLength = 3;
    public static Object[][] islandInstance = new Object[length][width];
    public static double plantGrowthRatio = 0.2;

    public Island() {
    }

    public static void setLength(int length) {
        Island.length = length;
    }

    public static void setWidth(int width) {
        Island.width = width;
    }

    public Object[][] getInstance() {
        return islandInstance;
    }


}