package com.javarush.main.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.main.enums.AnimalEnum;
import com.javarush.main.enums.AnimalParametersTypes;
import com.javarush.main.enums.TextMassages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {
    private static Properties properties;

    public void loadProperties () {
        try {
            InputStream input = new FileInputStream("src/com/javarush/resource/settings/game.properties");
            properties = new Properties();
            properties.load(input);
        } catch (IOException ex) {
            System.out.println(TextMassages.FAILURE_TO_LOAD_PROPERTIES);
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

    protected static void getValueOfEatingRatio (Map<String,Integer> map, AnimalEnum whoEat, AnimalEnum whomEat, AnimalParametersTypes animalParametersTypes) {
        //Map<String,Integer> map = new HashMap<String,Integer>();
        String propertyKey = whoEat.getName() + "_" + animalParametersTypes.getName() + "_"+ whomEat.getName();
        if (properties.getProperty(propertyKey) != null) {
            String animalEatable = whomEat.getName();
            Integer eatingRation = (Integer) Integer.parseInt(properties.getProperty(propertyKey));
            map.put(animalEatable, eatingRation);
        }
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
