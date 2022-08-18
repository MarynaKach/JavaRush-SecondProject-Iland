package com.javarush.main.game;

import java.util.List;

public class Island {
    private static int length = 100;
    private static int width = 20;
    private static int daysGameLasts = 5;
    private static List[][] island = new List[length][width];

    public Island() {
    }

    public static int getDaysGameLasts() {
        return daysGameLasts;
    }
    public static int getLength() {
        return length;
    }

    public static int getWidth() {
        return width;
    }

    public static List [][] getInstance() {
        if (island == null) {
            island = new List[length][width];
        }
        return island;
    }
}