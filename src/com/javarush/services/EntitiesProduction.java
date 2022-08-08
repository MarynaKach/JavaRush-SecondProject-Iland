package com.javarush.services;

import com.javarush.enums.AnimalEnum;
import com.javarush.enums.AnimalParametersTypes;
import com.javarush.enums.TextMassages;
import com.javarush.species.abstractclasses.Animal;
import com.javarush.species.abstractclasses.Entity;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class EntitiesProduction {

    GrassGrowth grassGrowth = new GrassGrowth();
    public static List<Entity> listOfEntitiesOnPosition = new ArrayList<>();

    public List<Entity> createListOfEntitiesOnPosition () {
        listOfEntitiesOnPosition.clear();
        listOfEntitiesOnPosition.addAll(createListOfRandomAnimals());
        //System.out.println(listOfEntitiesOnPosition.size());
        listOfEntitiesOnPosition.addAll(grassGrowth.setGrassOnPosition());
        //System.out.println(listOfEntitiesOnPosition.size());
        return listOfEntitiesOnPosition;
    }

    public List<Entity> createListOfRandomAnimals () {
        List<Entity> listOfRandomAnimals = new ArrayList<>();
        for(AnimalEnum animalEnum : AnimalEnum.values()) {
            double weight = Double.parseDouble(PropertiesLoader
                    .getAnimalProperties(animalEnum, AnimalParametersTypes.WEIGHT));
            int maxNumberOnPosition = Integer.parseInt(PropertiesLoader
                    .getAnimalProperties(animalEnum, AnimalParametersTypes.MAX_NUMBER_ON_POSITION), 10);
            int maxTravelSpeed = Integer.parseInt(PropertiesLoader.getAnimalProperties(animalEnum,
                    AnimalParametersTypes.MAX_TRAVEL_SPEED), 10);
            double kgForFullSaturation = Double.parseDouble(PropertiesLoader.getAnimalProperties(animalEnum,
                    AnimalParametersTypes.KG_FOR_FULL_SATURATION));
            int randomMaxNumberOnPosition = ThreadLocalRandom.current().nextInt(maxNumberOnPosition);
            for (int i = 0; i < randomMaxNumberOnPosition; i++) {
                try {
                    Animal animal = (Animal) animalEnum.getClazz()
                            .getConstructor(double.class, int.class, int.class, double.class)
                            .newInstance(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation);
                    listOfRandomAnimals.add(animal);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                        NoSuchMethodException e) {
                    System.out.println(TextMassages.FAILURE_TO_CREATE_INHABITANTS);
                    e.printStackTrace();

                }

            }

        }
        return  listOfRandomAnimals;
    }
}
