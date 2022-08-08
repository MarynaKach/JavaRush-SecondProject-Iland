package com.javarush.game;

import com.javarush.enums.DirectionsOfMoving;
import com.javarush.services.AnimalActions;
import com.javarush.services.EnumRandomChoice;
import com.javarush.services.PropertiesLoader;
import com.javarush.services.Statistic;

public class StartGame {
    IslandInitialization islandInitialization = new IslandInitialization();
    PropertiesLoader propertiesLoader = new PropertiesLoader();
    Statistic statistic = new Statistic();
    AnimalActions actions = new AnimalActions();
    public void play () {
        propertiesLoader.loadProperties();
        islandInitialization.islandInitialization();
        statistic.printTotalStatistic(islandInitialization.getInitializedIsland());

        int daysGameLasts = 1;
        for (int i = 0; i < daysGameLasts; i++) {
            actions.islandAnimalIteration();
            statistic.printTotalStatistic(islandInitialization.getInitializedIsland());
        }
    }
}
