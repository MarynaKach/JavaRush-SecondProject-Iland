package com.javarush.main.game;

public class Island {
    public int length;
    public int width;
    public int daysGameLasts;
    public int minLimitWidth;
    public int minLimitLength;
    public Object[][] islandInstance;
    public double plantGrowthRatio;

    public Island(int length, int width, int daysGameLasts, int minLimitWidth, int minLimitLength, double plantGrowthRatio, Object[][] islandInstance) {
        this.length = length;
        this.width = width;
        this.daysGameLasts = daysGameLasts;
        this.minLimitWidth = minLimitWidth;
        this.minLimitLength = minLimitLength;
        this.islandInstance = islandInstance;
        this.plantGrowthRatio = plantGrowthRatio;
    }

    public int getDaysGameLasts() {
        return daysGameLasts;
    }

    public Object[][] getIslandInstance() {
        return islandInstance;
    }
}
