package com.javarush.main.game;

import com.javarush.main.enums.TextMessages;
import com.javarush.main.services.*;

import java.util.List;
import java.util.concurrent.Phaser;

public class God {

    public void startSimulation() {
        IslandInitialization islandInitialization = new IslandInitialization();
        Island island = islandInitialization.createIslandWIthSpecifiedSize();
        Object[][] islandInstance = island.getIslandInstance();
        DisplacementOfPopulation displacementOfPopulation = new DisplacementOfPopulation();
        displacementOfPopulation.setEntitiesOnPosition(islandInstance);
        Statistic statistic = new Statistic();
        statistic.printStatisticOnStart(islandInstance);
        int gameLength = island.getDaysGameLasts();
        SupportingMethods supportingMethods = new SupportingMethods();
        Phaser phaser = new Phaser();

        for (int i = 0; i < gameLength; i++) {
            List<Runnable> listOfTasks = supportingMethods.getListOfTasksFirst(phaser, islandInstance);
            for (int k = 0; k < listOfTasks.size(); k++) {
                IslandEntityIterationRunnable threadPerPosition = (IslandEntityIterationRunnable) listOfTasks.get(k);
                try {
                    threadPerPosition.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.printf(TextMessages.STATISTIC_ON_BEGINNING_OF_DAY.getMassage(), i + 1);
            statistic.countStatisticOfDays(islandInstance);
        }
    }
}
