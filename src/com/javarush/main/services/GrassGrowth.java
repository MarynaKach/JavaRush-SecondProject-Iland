package com.javarush.main.services;

import com.javarush.main.game.Island;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class GrassGrowth {
    //Island island = new Island();

   protected List<Entity> setGrassOnPosition () {
       List<Entity> randomNumberOfGrass = new ArrayList<>();
       randomNumberOfGrass.clear();
       Grass grass = createGrass();
       int maxNumberOfGrass = grass.getMaxNumberOnPosition();
       for (int i = 0; i < ThreadLocalRandom.current().nextInt(maxNumberOfGrass); i++) {
           randomNumberOfGrass.add(createGrass());
       }
       if (randomNumberOfGrass.size() == 0) {
           setGrassOnPosition();
       }
       return  randomNumberOfGrass;
   }

    protected void letPlantGrowAtNight(List<List<List<Entity>>>islandInstance, String plantName) {
        List<Entity> newGrassList = new ArrayList<>();
        for (int row = 0; row < islandInstance.size(); row++) {
            for (int columns = 0; columns < islandInstance.get(row).size(); columns++) {
                List<Entity> listOfEntitiesOnPosition = islandInstance.get(row).get(columns);
                long numberOfGrassAtEndOFDay = listOfEntitiesOnPosition.stream()
                        .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(plantName))
                        .count();
                if (numberOfGrassAtEndOFDay == 0) {
                    newGrassList = setGrassOnPosition();
                    listOfEntitiesOnPosition.addAll(newGrassList);
                }
                if (numberOfGrassAtEndOFDay > 0) {
                    double plantGrowthRation = Island.plantGrowthRatio;
                    int grassIncreasingRatio = (int) Math.ceil(numberOfGrassAtEndOFDay * plantGrowthRation);
                    for (int i = 0; i < grassIncreasingRatio; i++) {
                        newGrassList.add(createGrass());
                    }
                }
                listOfEntitiesOnPosition.addAll(newGrassList);
                newGrassList.clear();
            }
        }
    }

    private Grass createGrass () {
        int weight = Integer.parseInt(PropertiesLoader.getPlantProperties("Grass", "weight"));
        int maxNumberOfGrass = Integer.parseInt(PropertiesLoader.getPlantProperties("Grass", "maxNumberOfGrass"));
        return new Grass (weight, maxNumberOfGrass);
    }
}
