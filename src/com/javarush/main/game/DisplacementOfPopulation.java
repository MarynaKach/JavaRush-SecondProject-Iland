package com.javarush.main.game;

import com.javarush.main.services.EntitiesProduction;

public class DisplacementOfPopulation {
    private EntitiesProduction entitiesProduction = new EntitiesProduction();

    public void setEntitiesOnPosition(Object[][] islandInstance) {
        for (int row = 0; row < islandInstance.length; row++) {
            for (int columns = 0; columns < islandInstance[row].length; columns++) {
                islandInstance[row][columns] = entitiesProduction.createListOfEntitiesOnPosition();
            }
        }
    }
}
