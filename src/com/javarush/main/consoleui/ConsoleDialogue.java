package com.javarush.main.consoleui;

import com.javarush.main.enums.AnimalParametersTypes;
import com.javarush.main.enums.TextMessages;
import com.javarush.main.services.PropertiesLoader;

public class ConsoleDialogue {
    int defaultIslandWidth = Integer.parseInt(PropertiesLoader
            .properties.getProperty("Island_" + AnimalParametersTypes.WIDTH.getName()));
    int defaultIslandLength = Integer.parseInt(PropertiesLoader
            .properties.getProperty("Island_" + AnimalParametersTypes.LENGTH.getName()));

    public boolean startDialogue() {
        System.out.printf(TextMessages.VELCOM_MASSAGE.getMassage(), defaultIslandWidth, defaultIslandLength);
        System.out.println(TextMessages.OPTION_TO_CHANGE_ISLAND_SIZE.getMassage());
        String answerYesOrNo = ScannerSingleton.getInstance().nextLine();
        boolean ifEqualYes = checkIfAnswerYesOrNot(answerYesOrNo, TextMessages.ANSWER_YES);
        boolean ifEqualNot = checkIfAnswerYesOrNot(answerYesOrNo, TextMessages.ANSWER_NOT);
        while (!ifEqualYes && !ifEqualNot) {
            System.out.println(TextMessages.OPTION_TO_CHANGE_ISLAND_SIZE.getMassage());
            answerYesOrNo = ScannerSingleton.getInstance().nextLine();
            ifEqualYes = checkIfAnswerYesOrNot(answerYesOrNo, TextMessages.ANSWER_YES);
            ifEqualNot = checkIfAnswerYesOrNot(answerYesOrNo, TextMessages.ANSWER_NOT);
        }
        return ifEqualYes;
    }

    private boolean checkIfAnswerYesOrNot(String answer, TextMessages textMassages) {
        return answer.equalsIgnoreCase(textMassages.getMassage());
    }
}
