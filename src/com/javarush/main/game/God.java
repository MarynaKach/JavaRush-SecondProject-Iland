package com.javarush.main.game;

import com.javarush.main.consoleui.ConsoleDialogue;
import com.javarush.main.enums.TextMessages;
import com.javarush.main.services.IslandEntityIteration;
import com.javarush.main.services.Statistic;

public class God {
    Island island;

    public void startSimulation() {
        ConsoleDialogue consoleDialogue = new ConsoleDialogue();
        boolean ifChangeIslandSize = consoleDialogue.startDialogue();
        IslandInitialization islandInitialization = new IslandInitialization();
        if (ifChangeIslandSize) {
            islandInitialization.chooseNewIslandSize();
            islandInitialization.getNewIslandLandLength();
            islandInitialization.getNewIslandLandWidth();
            int newIslandWidth = islandInitialization.getNewIslandLandWidth();
            int newIslandLength = islandInitialization.getNewIslandLandLength();
            island = islandInitialization.createIsland(newIslandWidth, newIslandLength);
        } else {
            int defaultIslandWidth = islandInitialization.getDefaultIslandWidth();
            int defaultIslandLength = islandInitialization.getDefaultIslandLength();
            island = islandInitialization
                    .createIsland(defaultIslandWidth, defaultIslandLength);
        }
        Object[][] islandInstance = island.getIslandInstance();
        DisplacementOfPopulation displacementOfPopulation = new DisplacementOfPopulation();
        displacementOfPopulation.setEntitiesOnPosition(islandInstance);
        Statistic statistic = new Statistic();
        statistic.printStatisticOnStart(islandInstance);
        int gameLength = island.getDaysGameLasts();
        IslandEntityIteration animalMakeActions = new IslandEntityIteration();
        for (int i = 0; i < gameLength; i++) {
            animalMakeActions.islandAnimalIteration(islandInstance);
            System.out.printf(TextMessages.STATISTIC_ON_END_OF_DAY.getMassage(), i+1);
            statistic.countStatisticOfDays(islandInstance);
        }
    }
}
