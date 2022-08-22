package com.javarush.main.game;

import com.javarush.main.services.EntitiesProduction;

public class IslandInitialization {

    public EntitiesProduction entitiesProduction = new EntitiesProduction();
   //Island island = new Island();
    //Object[][] islandInstance = island.getInstance();
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
        Object[][] islandInstance = Island.islandInstance;
        setEntitiesOnPosition (islandInstance);
        return islandInstance;
    }

   /* private Object [][] makeNullEdgesOfIsland (Object[][] island) {
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
    }*/

    private Object [][] setEntitiesOnPosition (Object [][] islandInstance) {
        for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {
                islandInstance[row][columns] = entitiesProduction.createListOfEntitiesOnPosition();
            }
        }
        return  islandInstance;
    }
}

