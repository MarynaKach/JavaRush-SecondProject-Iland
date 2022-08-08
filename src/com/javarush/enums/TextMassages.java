package com.javarush.enums;

public enum TextMassages {
    STATISTIC_ON_BEGINNING_OF_GAME ("The statistic of animals and grass on the Island on the start of the game: "),
    NUMBER_OF_PREDATORS("The number of predators on the Island is: "),
    NUMBER_OF_HERBIVORES("The number of herbivores on the Island is: "),
    NUMBER_OF_GRASS("The number of plant on the Island is: "),
    NO_CHOOSING_DIRECTION ("The direction of moving wasn't been chosen. Please start again."),
    FAILURE_TO_CHOOSE_ACTION ("The action wasn't been chosen. Please start again."),
    FAILURE_TO_GET_MAX_NUMBER_ON_POSITION ("Failure to get the parameter \"maxNumberOnPosition\". "),
    FAILURE_TO_CREATE_INHABITANTS ("The inhabitants of the Island couldn't be created. Please stat again");

    private String massage;

    TextMassages(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
