package com.javarush.main.game;

import com.javarush.main.species.abstractclasses.Entity;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private int length = 100;
    private int width = 20;
    public static int daysGameLasts = 5;
    public static int minLimitWidth = 3;
    public static int minLimitLength = 3;
    public static List<List<List<Entity>>> islandInstance = new ArrayList<>();
    public static double plantGrowthRatio = 0.2;

    public Island() {
    }

    public static int getDaysGameLasts() {
        return daysGameLasts;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public List<List<List<Entity>>> getInstance() {
        return islandInstance;
    }


}