package com.javarush.main.consoleui;

import com.javarush.main.enums.TextMassages;
import com.javarush.main.game.Island;
import com.javarush.main.game.IslandInitialization;
import com.javarush.main.services.Statistic;
import java.lang.reflect.Field;
import java.util.Arrays;

// TO DO join to massages in one, join methods typeLength and typeWidth, join methods changeIslandLength and changeIslandWidth
public class ConsoleDialogue {
    Object[][] island = Island.getInstance();
    Statistic statistic = new Statistic();
    IslandInitialization islandInitialization = new IslandInitialization();
    int width = Island.getWidth();
    int length = Island.getLength();

    public void startDialogue() {
        System.out.printf(TextMassages.VELCOM_MASSAGE.getMassage(), width, length);
        System.out.println(TextMassages.OPTION_TO_CHANGE_ISLAND_SIZE.getMassage());
        // Join to massages
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
            chooseIslandSize ();
        } if(ifEqualNot ) {
            statistic.printTotalStatistic(islandInitialization.islandInitialization());
        }
    }

    private void chooseIslandSize () {
        int newWidth = typeIslandWidth();
        int newLength = typeIslandLength();
        System.out.printf(TextMassages.NEW_SIZE_OF_ISLAND.getMassage(), newWidth, newLength);
        String answerYesOrNo = ScannerSingleton.getInstance().nextLine();
        boolean ifEqualYes = answerYesOrNo.equalsIgnoreCase(TextMassages.ANSWER_YES.getMassage());
        boolean ifEqualNot = answerYesOrNo.equalsIgnoreCase(TextMassages.ANSWER_NOT.getMassage());
        while (!ifEqualYes && !ifEqualNot) {
            System.out.println(TextMassages.OPTION_TO_CHANGE_ISLAND_SIZE.getMassage());
            answerYesOrNo = ScannerSingleton.getInstance().nextLine();
            ifEqualYes = answerYesOrNo.equalsIgnoreCase(TextMassages.ANSWER_YES.getMassage());
            ifEqualNot = answerYesOrNo.equalsIgnoreCase(TextMassages.ANSWER_NOT.getMassage());
        }

        if (ifEqualYes) {
            chooseIslandSize();
        }
        if (ifEqualNot) {
            changeIslandLength(island, newLength);
            changeIslandWidth(island, newWidth);

            statistic.printTotalStatistic(islandInitialization.islandInitialization());
            System.out.println(Arrays.deepToString(island));
        }
    }

    private int typeIslandWidth () {
        int lowLimitWidth = 3;
        int highLimitWidth = 19;
        System.out.printf(TextMassages.TYPE_NEW_WIDTH.getMassage(), lowLimitWidth, highLimitWidth);
        int newWidth = ScannerSingleton.getInstance().nextInt();
        while (newWidth < lowLimitWidth || newWidth > highLimitWidth) {
            System.out.printf(TextMassages.TYPE_NEW_WIDTH.getMassage(), lowLimitWidth, highLimitWidth);
            newWidth = ScannerSingleton.getInstance().nextInt();
        }
        return newWidth;
    }

    private int typeIslandLength () {
        int lowLimitLength = 3;
        int highLimitLength = 99;
        System.out.printf(TextMassages.TYPE_NEW_LENGTH.getMassage(), lowLimitLength, highLimitLength);
        int newLength = ScannerSingleton.getInstance().nextInt();
        while (newLength < lowLimitLength || newLength > highLimitLength) {
            System.out.printf(TextMassages.TYPE_NEW_LENGTH.getMassage(), lowLimitLength, highLimitLength);
            newLength = ScannerSingleton.getInstance().nextInt();
        }
        return newLength;
    }
// TO DO the size of island isn't changed, check why
    private void changeIslandLength (Object[][] island, int newLength) {
        try {
            Field field = Island.class.getDeclaredField("length");
            field.setAccessible(true);
            field.set(island, newLength);
            length = (int) field.get(field);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Couldn't get field length.");
            e.printStackTrace();
        }
    }
    private void changeIslandWidth (Object[][] island, int newWidth) {
        try {
            Field field = Island.class.getDeclaredField("width");
            field.setAccessible(true);
            field.set(island, newWidth);
            width = (int) field.get(field);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            System.out.println("Couldn't get field width.");
            e.printStackTrace();
        }
    }
}
