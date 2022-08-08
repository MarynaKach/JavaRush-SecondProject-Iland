package com.javarush.services;

import com.javarush.enums.AnimalEnum;
import com.javarush.enums.AnimalParametersTypes;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static Properties properties;

    public void loadProperties () {
        try {
            InputStream input = new FileInputStream("D:\\Masha\\JetBrains\\JavaRush-SecondProject-Iland\\src\\com\\javarush\\settings\\game.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            System.out.println("Couldn't load Game Properties, please try again.");
            ex.printStackTrace();
        }
    }
    /*protected static double  getDoubleAnimalProperties(Enum AnimalEnum, Enum AnimalParametersTypes) {
        String propertyKey = AnimalEnum.name() + "_" + AnimalParametersTypes.name();
        double property = properties.getProperty(propertyKey);
        return property;
    }*/
    protected static String  getAnimalProperties (AnimalEnum animalEnum, AnimalParametersTypes animalParametersTypes) {
        String propertyKey = animalEnum.getName() + "_" + animalParametersTypes.getName();
        return  properties.getProperty(propertyKey);
    }

    protected static String  getEatingRatio (AnimalEnum animalEnum, AnimalParametersTypes animalParametersTypes) {
        String eatingRation = animalEnum.getName() + "_possibilityToEat_" + animalEnum.getName();
        return  properties.getProperty(eatingRation);
    }

    protected static String getGrassProperties(String className, String propertyName) {
        String propertyKey = className + "_" + propertyName;
        return  properties.getProperty(propertyKey);
    }

}
