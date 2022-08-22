package com.javarush.main.game;

import com.javarush.main.services.EntitiesProduction;
import com.javarush.main.species.abstractclasses.Entity;

import java.util.ArrayList;
import java.util.List;

public class IslandInitialization {
    List<List<List<Entity>>> islandInstance = Island.islandInstance;

    public EntitiesProduction entitiesProduction = new EntitiesProduction();
   Island island = new Island();


    public List<List<List<Entity>>> islandInitialization () {
        setEntitiesOnPosition (islandInstance);
        return islandInstance;
    }

    private List<List<List<Entity>>> setEntitiesOnPosition (List<List<List<Entity>>>islandInstance) {
        int islandLength = island.getLength();
        int islandWidth = island.getWidth();
        for (int row = 0; row < islandLength; row++) {
            List<List<Entity>> columnsList = new ArrayList<>();
            for(int columns = 0; columns < islandWidth; columns++) {
                columnsList.add(entitiesProduction.createListOfEntitiesOnPosition());
            }
            islandInstance.add(columnsList);
        }
        return  islandInstance;
    }
}

