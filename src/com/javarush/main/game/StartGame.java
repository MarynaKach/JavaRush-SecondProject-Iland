package com.javarush.main.game;

import com.javarush.main.consoleui.ConsoleDialogue;
import com.javarush.main.services.AnimalActions;
import com.javarush.main.services.PropertiesLoader;
import com.javarush.main.services.Statistic;

import java.util.Arrays;

public class StartGame {
    PropertiesLoader propertiesLoader = new PropertiesLoader();
    ConsoleDialogue consoleDialogue = new ConsoleDialogue();
    IslandInitialization islandInitialization;
    Statistic statistic;
    AnimalActions actions = new AnimalActions();

    public void play() {
        //Object[][] island = Island.getInstance();
        //System.out.println(Arrays.deepToString(island));
            propertiesLoader.loadProperties();
            consoleDialogue.startDialogue();
            //statistic.printTotalStatistic(islandInitialization.islandInitialization());

            int daysGameLasts = 5;
            for (int i = 0; i < daysGameLasts; i++) {
                actions.islandAnimalIteration();
                statistic.printTotalStatistic(Island.getInstance());
            }

    }
}
