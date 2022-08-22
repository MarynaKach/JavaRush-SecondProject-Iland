package com.javarush.main.consoleui;

import com.javarush.main.enums.TextMassages;
import com.javarush.main.game.Island;
import com.javarush.main.game.IslandInitialization;
import com.javarush.main.services.Statistic;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class ConsoleDialogue {
    Island island = new Island();
    Statistic statistic = new Statistic();
    IslandInitialization islandInitialization = new IslandInitialization();

    public void startDialogue() {
        System.out.printf(TextMassages.VELCOM_MASSAGE.getMassage(), island.getWidth(), island.getLength());
        System.out.println(TextMassages.OPTION_TO_CHANGE_ISLAND_SIZE.getMassage());
        String answerYesOrNo = ScannerSingleton.getInstance().nextLine();
        boolean ifEqualYes = answerYesOrNo.equalsIgnoreCase(TextMassages.ANSWER_YES.getMassage());
        boolean ifEqualNot = answerYesOrNo.equalsIgnoreCase(TextMassages.ANSWER_NOT.getMassage());
        while ( !ifEqualYes && !ifEqualNot) {
            System.out.println(TextMassages.OPTION_TO_CHANGE_ISLAND_SIZE.getMassage());
            answerYesOrNo = ScannerSingleton.getInstance().nextLine();
            ifEqualYes = answerYesOrNo.equalsIgnoreCase(TextMassages.ANSWER_YES.getMassage());
            ifEqualNot = answerYesOrNo.equalsIgnoreCase(TextMassages.ANSWER_NOT.getMassage());
        }
        if(ifEqualYes){
            chooseNewIslandSize();
            statistic.printStatisticOnStart(islandInitialization.islandInitialization());
        } if(ifEqualNot ) {
            statistic.printStatisticOnStart(islandInitialization.islandInitialization());
        }
    }

    private int typeNewIslandSize(TextMassages textMassages, int lowLimitSize) {
        System.out.printf(textMassages.getMassage(), lowLimitSize);
        String  newSize = ScannerSingleton.getInstance().nextLine();
        while (!Pattern.matches("[0-9]+", newSize) || Integer.parseInt(newSize) < lowLimitSize) {
            System.out.printf(textMassages.getMassage(), lowLimitSize);
            newSize = ScannerSingleton.getInstance().nextLine();
        }
        return Integer.parseInt(newSize);
    }

    private void chooseNewIslandSize() {
        int newWidth = typeNewIslandSize(TextMassages.TYPE_NEW_WIDTH, Island.minLimitWidth);//typeIslandWidth();
        int newLength = typeNewIslandSize(TextMassages.TYPE_NEW_LENGTH, Island.minLimitLength);//typeIslandLength();
        System.out.printf(TextMassages.NEW_SIZE_OF_ISLAND.getMassage(), newWidth, newLength);
        changeIslandSize(island, newLength, "length");
        changeIslandSize(island, newLength, "width");
    }



   private void changeIslandSize (Island island, int newSize, String fieldName) {
        try {
            Field field = Island.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(island, newSize);
            field.setAccessible(false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            System.out.println(TextMassages.FAILURE_TO_GET_ISLAND_SIZE.getMassage());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            System.out.println(TextMassages.FAILURE_TO_GET_ISLAND_SIZE.getMassage());
        }
   }
}
