package com.javarush.main.services;

import com.javarush.main.enums.AnimalEnum;
import com.javarush.main.enums.AnimalParametersTypes;
import com.javarush.main.enums.TextMessages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {
    public static Properties properties;

    public void loadProperties() {
        try {
            InputStream input = new FileInputStream("src/com/javarush/resource/settings/game.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            System.out.println(TextMessages.FAILURE_TO_LOAD_PROPERTIES);
            ex.printStackTrace();
        }
    }

    protected static String getAnimalProperties(AnimalEnum animalEnum, AnimalParametersTypes animalParametersTypes) {
        String propertyKey = animalEnum.getName() + "_" + animalParametersTypes.getName();
        return properties.getProperty(propertyKey);
    }

    protected static void getValueOfEatingRatio(Map<String, Integer> map, AnimalEnum whoEat, AnimalEnum whomEat, AnimalParametersTypes animalParametersTypes) {
        String propertyKey = whoEat.getName() + "_" + animalParametersTypes.getName() + "_" + whomEat.getName();
        if (properties.getProperty(propertyKey) != null) {
            String animalEatable = whomEat.getName();
            Integer eatingRation = Integer.parseInt(properties.getProperty(propertyKey));
            map.put(animalEatable, eatingRation);
        }
    }

    protected static String getPlantProperties(String className, String propertyName) {
        String propertyKey = className + "_" + propertyName;
        return properties.getProperty(propertyKey);
    }
}
