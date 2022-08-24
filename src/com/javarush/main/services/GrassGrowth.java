package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class GrassGrowth {

    protected List<Entity> setGrassOnPosition() {
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
        return randomNumberOfGrass;
    }

    protected void letPlantGrowAtNight(Object[][] islandInstance, String plantName) {
        List<Entity> newGrassList = new ArrayList<>();
        for (int row = 1; row < islandInstance.length; row++) {
            for (int columns = 1; columns < islandInstance[row].length; columns++) {
                List<Entity> listOfEntitiesOnPosition = (List<Entity>) islandInstance[row][columns];
                long numberOfGrassAtEndOFDay = listOfEntitiesOnPosition.stream()
                        .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(plantName))
                        .count();
                if (numberOfGrassAtEndOFDay == 0) {
                    newGrassList = setGrassOnPosition();
                    listOfEntitiesOnPosition.addAll(newGrassList);
                }
                if (numberOfGrassAtEndOFDay > 0) {
                    double plantGrowthRation = 0.2;
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

    private Grass createGrass() {
        int weight = Integer.parseInt(PropertiesLoader.getPlantProperties("Grass", "weight"));
        int maxNumberOfGrass = Integer.parseInt(PropertiesLoader.getPlantProperties("Grass", "maxNumberOfGrass"));
        return new Grass(weight, maxNumberOfGrass);
    }
}
