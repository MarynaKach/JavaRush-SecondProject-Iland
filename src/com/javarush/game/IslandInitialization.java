package com.javarush.game;
import com.javarush.services.EntitiesProduction;


public class IslandInitialization {

    public EntitiesProduction entitiesProduction = new EntitiesProduction();
    private int length = 100;
    private int width = 20;
    private Object[][] initializedIsland = new Object[length][width];

    public Object[][] getInitializedIsland() {
        return initializedIsland;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public void islandInitialization () {
        makeNullEdgesOfIsland(initializedIsland);
        setEntitiesOnPosition (initializedIsland);

    }

    private Object[][] makeNullEdgesOfIsland (Object[][] initializedIsland) {
        for (int row = 0; row < length; row++) {
            for (int columns = 0; columns < width; columns++) {
                if (row == 0) {
                    initializedIsland[row][columns] = null;
                }
                if (columns == 0) {
                    initializedIsland[row][columns] = null;
                }
                if(columns == width-1) {
                    initializedIsland[row][columns] = null;
                }
                if (row == length - 1) {
                    initializedIsland[row][columns] = null;
                }
            }
        }
        return initializedIsland;
    }

    private void setEntitiesOnPosition (Object[][] initializedIsland) {
        makeNullEdgesOfIsland(initializedIsland);
        for (int row = 1; row < length-1; row++) {
            for (int columns = 1; columns < width - 1; columns++) {
                initializedIsland[row][columns] = entitiesProduction.createListOfEntitiesOnPosition();
            }
        }
    }
}

