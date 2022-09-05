package com.javarush.main.game;

import com.javarush.main.enums.TextMessages;
import com.javarush.main.services.Statistic;
import com.javarush.main.services.IslandEntityIteration;

import java.util.concurrent.Phaser;

public class God {

    public void startSimulation() {
        IslandInitialization islandInitialization = new IslandInitialization();
        DisplacementOfPopulation displacementOfPopulation = new DisplacementOfPopulation();
        Statistic statistic = new Statistic();

        Island island = islandInitialization.createIslandWIthSpecifiedSize();
        Object[][] islandInstance = island.getIslandInstance();
        displacementOfPopulation.setEntitiesOnPosition(islandInstance);
        System.out.println(TextMessages.STATISTIC_ON_BEGINNING_OF_GAME.getMassage());
        statistic.countStatisticOfDays(islandInstance);
        int gameLength = island.getDaysGameLasts();
        for (int i = 0; i < gameLength; i++) {
            Phaser phaser = new Phaser();
            Thread thread = null;
            for (int row = 0; row < islandInstance.length; row++) {
                for (int columns = 0; columns < islandInstance[row].length; columns++) {
                    IslandEntityIteration islandEntityIterationRunnable = new IslandEntityIteration(phaser, islandInstance, row, columns);
                    thread = new Thread(islandEntityIterationRunnable);
                    thread.start();
                }
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    System.out.println(TextMessages.FAILURE_TO_JOIN_THREAD);
                    e.printStackTrace();
                }
            }
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndAwaitAdvance();
            phaser.arriveAndDeregister();
            System.out.printf(TextMessages.STATISTIC_ON_BEGINNING_OF_DAY.getMassage(), i + 2);
            statistic.countStatisticOfDays(islandInstance);
        }
    }
}
