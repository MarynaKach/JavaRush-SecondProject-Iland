package com.javarush.main.game;

import com.javarush.main.enums.TextMessages;
import com.javarush.main.services.*;

import java.util.List;
import java.util.concurrent.Phaser;

public class God {

    public void startSimulation() {
        IslandInitialization islandInitialization = new IslandInitialization();
        DisplacementOfPopulation displacementOfPopulation = new DisplacementOfPopulation();
        Statistic statistic = new Statistic();
        SupportingMethods supportingMethods = new SupportingMethods();
        Phaser phaser = new Phaser();

        Island island = islandInitialization.createIslandWIthSpecifiedSize();
        Object[][] islandInstance = island.getIslandInstance();
        displacementOfPopulation.setEntitiesOnPosition(islandInstance);
        statistic.printStatisticOnStart(islandInstance);
        int gameLength = island.getDaysGameLasts();
        for (int i = 0; i < gameLength; i++) {
            List<Runnable> listOfTasks = supportingMethods.getThreadTaskPerPosition(phaser, islandInstance);
            for (int k = 0; k < listOfTasks.size(); k++) {
                IslandEntityIterationRunnable threadPerPosition = (IslandEntityIterationRunnable) listOfTasks.get(k);
                try {
                    threadPerPosition.join();
                } catch (InterruptedException e) {
                    System.out.println(TextMessages.FAILURE_TO_JOIN_THREAD);
                    e.printStackTrace();
                }
            }
            System.out.printf(TextMessages.STATISTIC_ON_BEGINNING_OF_DAY.getMassage(), i + 1);
            statistic.countStatisticOfDays(islandInstance);
        }
    }
}
