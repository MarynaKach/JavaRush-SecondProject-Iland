package com.javarush.main.game;

import com.javarush.main.services.EntitiesProduction;

public class IslandInitialization {

    public EntitiesProduction entitiesProduction = new EntitiesProduction();
   /* private static int length = 100;
    private static int width = 20;
    private static Object[][] initializedIsland = new Object[length][width];

    public Object[][] getInitializedIsland() {
        return initializedIsland;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }*/

    public Object[][] islandInitialization () {
        Object[][] island = Island.getInstance();
        makeNullEdgesOfIsland(island);
        setEntitiesOnPosition (island);
        return island;
    }

    private Object [][] makeNullEdgesOfIsland (Object[][] island) {
        for (int row = 0; row < island.length; row++) {
            for (int columns = 0; columns < island[row].length; columns++) {
                if (row == 0) {
                    island[row][columns] = null;
                }
                if (columns == 0) {
                    island[row][columns] = null;
                }
                if(columns == island[row].length-1) {
                    island[row][columns] = null;
                }
                if (row == island.length - 1) {
                    island[row][columns] = null;
                }
            }
        }
        return island;
    }

    private Object [][] setEntitiesOnPosition (Object [][] island) {
        makeNullEdgesOfIsland(island);
        for (int row = 1; row < island.length-1; row++) {
            for (int columns = 1; columns < island[row].length - 1; columns++) {
                island[row][columns] = entitiesProduction.createListOfEntitiesOnPosition();
            }
        }
        return  island;
    }
}

