package com.javarush.main.services;

import com.javarush.main.species.abstractclasses.Entity;
import com.javarush.main.species.plant.Grass;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class GrassGrowth {


   public List<Entity> setGrassOnPosition () {
       List<Entity> randomNumberOfGrass = new ArrayList<>();
       randomNumberOfGrass.clear();
       int maxNumberOfGrass = Integer.parseInt(PropertiesLoader.getGrassProperties("Grass", "maxNumberOfGrass"));
       int weight = Integer.parseInt(PropertiesLoader.getGrassProperties("Grass", "weight"));
       for (int i = 0; i < ThreadLocalRandom.current().nextInt(maxNumberOfGrass); i++) {
           randomNumberOfGrass.add(new Grass(weight, maxNumberOfGrass));

       }
       if (randomNumberOfGrass.size() == 0) {
           setGrassOnPosition();
       }
       return  randomNumberOfGrass;
   }
}
