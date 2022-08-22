package com.javarush.main.services;

import com.javarush.main.enums.AnimalEnum;
import com.javarush.main.enums.AnimalParametersTypes;
import com.javarush.main.enums.TextMassages;
import com.javarush.main.species.abstractclasses.Animal;
import com.javarush.main.species.abstractclasses.Entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


public class EntitiesProduction {
    GrassGrowth grassGrowth = new GrassGrowth();


    public List<Entity> createListOfEntitiesOnPosition() {
        List<Entity> listOfEntitiesOnPosition = new ArrayList<>();
        listOfEntitiesOnPosition.clear();
        listOfEntitiesOnPosition.addAll(createListOfRandomAnimals());
        listOfEntitiesOnPosition.addAll(grassGrowth.setGrassOnPosition());
        return listOfEntitiesOnPosition;
    }

    private List<Entity> createListOfRandomAnimals() {
        List<Entity> listOfRandomAnimals = new ArrayList<>();
        for (AnimalEnum animalEnum : AnimalEnum.values()) {
            String entityName = animalEnum.getName();
            if (!(entityName.contains("Grass"))) {
                for (int i = 0; i < getRandomMaxNumberOnPosition(animalEnum); i++) {
                    Animal animal = createAnimal(animalEnum);
                    listOfRandomAnimals.add(animal);
                }
            }
        }
        return listOfRandomAnimals;
    }



    private void getEatingRatio(HashMap<String, Integer> animalEatingRatio, AnimalEnum whoEat) {
        for(AnimalEnum whomEat : AnimalEnum.values()) {
            PropertiesLoader.getValueOfEatingRatio(animalEatingRatio, whoEat, whomEat, AnimalParametersTypes.POSSIBILITY_TO_EAT);
        }
    }

    private AnimalEnum findAnimalEnum(Animal animal) {
        AnimalEnum animalEnumName = null;
        for (AnimalEnum animalEnum : AnimalEnum.values()) {
            if (animalEnum.getClazz() == animal.getClass()) {
                animalEnumName =   animalEnum;
            }
        }
        return animalEnumName;
    }

    protected Entity createNewBornAnimal(Animal animal) {
        Animal newBornAnimal = null;
        AnimalEnum animalEnum = findAnimalEnum(animal);
        newBornAnimal = createAnimal(animalEnum);
        newBornAnimal.setActionDone(true);
        return newBornAnimal;
    }

    private Animal createAnimal (AnimalEnum animalEnum) {
        Animal animal = null;
        double weight = Double.parseDouble(PropertiesLoader
                .getAnimalProperties(animalEnum, AnimalParametersTypes.WEIGHT));
        int maxNumberOnPosition = Integer.parseInt(PropertiesLoader
                .getAnimalProperties(animalEnum, AnimalParametersTypes.MAX_NUMBER_ON_POSITION), 10);
        int maxTravelSpeed = Integer.parseInt(PropertiesLoader.getAnimalProperties(animalEnum,
                AnimalParametersTypes.MAX_TRAVEL_SPEED), 10);
        double kgForFullSaturation = Double.parseDouble(PropertiesLoader.getAnimalProperties(animalEnum,
                AnimalParametersTypes.KG_FOR_FULL_SATURATION));
        boolean ifActionDone = false;
        HashMap<String, Integer> animalEatingRatio = new HashMap<>();
        getEatingRatio(animalEatingRatio, animalEnum);
        int saturationRatio = Integer.parseInt(PropertiesLoader.getAnimalProperties(animalEnum,
                AnimalParametersTypes.SATURATION_RATIO), 10);
        try {
            animal = (Animal) animalEnum.getClazz()
                    .getConstructor(double.class, int.class, int.class, double.class, boolean.class,
                            HashMap.class, int.class)
                    .newInstance(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, ifActionDone,
                            animalEatingRatio, saturationRatio);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                NoSuchMethodException e) {
            System.out.println(TextMassages.FAILURE_TO_CREATE_INHABITANTS);
            e.printStackTrace();
        }
        return animal;
    }

    private int getRandomMaxNumberOnPosition (AnimalEnum animalEnum) {
        int maxNumberOnPosition = Integer.parseInt(PropertiesLoader
                .getAnimalProperties(animalEnum, AnimalParametersTypes.MAX_NUMBER_ON_POSITION), 10);
        return ThreadLocalRandom.current().nextInt(maxNumberOnPosition);
    }
}
