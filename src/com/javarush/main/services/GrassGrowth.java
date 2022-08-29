package com.javarush.main.services;

import com.javarush.main.game.IslandInitialization;
import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GrassGrowth {

    public void letPlantGrowAtNight(Object[][] islandInstance, String plantName, int row, int columns) {
        CopyOnWriteArrayList<Entity> newGrassList = new CopyOnWriteArrayList<>();
        List<Entity> entitiesOnPosition = (List<Entity>) islandInstance[row][columns];
        long numberOfGrassAtEndOFDay = entitiesOnPosition.stream()
                .filter(x -> x.getClass().getSimpleName().equalsIgnoreCase(plantName))
                .count();
        if (numberOfGrassAtEndOFDay == 0) {
            newGrassList = setGrassOnPosition();
            entitiesOnPosition.addAll(newGrassList);
        }
        if (numberOfGrassAtEndOFDay > 0) {
            IslandInitialization islandInitialization = new IslandInitialization();
            double plantGrowthRation = islandInitialization.getPlantGrowthRatio();
            int grassIncreasingRatio = (int) Math.ceil(numberOfGrassAtEndOFDay * plantGrowthRation);
            for (int i = 0; i < grassIncreasingRatio; i++) {
                newGrassList.add(createGrass());
            }
            entitiesOnPosition.addAll(newGrassList);
        }
        newGrassList.clear();
    }

    protected CopyOnWriteArrayList<Entity> setGrassOnPosition() {
        CopyOnWriteArrayList<Entity> randomNumberOfGrass = new CopyOnWriteArrayList<>();
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

    private Grass createGrass() {
        int weight = Integer.parseInt(PropertiesLoader.getPlantProperties("Grass", "weight"));
        int maxNumberOfGrass = Integer.parseInt(PropertiesLoader.getPlantProperties("Grass", "maxNumberOfGrass"));
        return new Grass(weight, maxNumberOfGrass);
    }
}
