package com.javarush.main.game;

import com.javarush.main.consoleui.ConsoleDialogue;
import com.javarush.main.enums.TextMassages;
import com.javarush.main.services.AnimalMakeActions;
import com.javarush.main.services.PropertiesLoader;
import com.javarush.main.services.Statistic;

public class StartGame {
    PropertiesLoader propertiesLoader = new PropertiesLoader();
    ConsoleDialogue consoleDialogue = new ConsoleDialogue();
    Statistic statistic = new Statistic();
    AnimalMakeActions actions = new AnimalMakeActions();
    //Island island = new Island();


    public void play() {
            propertiesLoader.loadProperties();
            consoleDialogue.startDialogue();
            int daysGameLasts = Island.daysGameLasts;
            for (int i = 0; i < daysGameLasts; i++) {
                actions.islandAnimalIteration();
                System.out.printf(TextMassages.STATISTIC_ON_BEGINNING_OF_DAY.getMassage(), i+1);
                statistic.countStatisticOfDays(Island.islandInstance);
            }

    }
}
