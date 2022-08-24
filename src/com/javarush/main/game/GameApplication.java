package com.javarush.main.game;

import com.javarush.main.services.PropertiesLoader;

public class GameApplication {
    public static void main(String[] args) {
        PropertiesLoader propertiesLoader = new PropertiesLoader();
        propertiesLoader.loadProperties();
        God god = new God();
        god.startSimulation();
    }
}
