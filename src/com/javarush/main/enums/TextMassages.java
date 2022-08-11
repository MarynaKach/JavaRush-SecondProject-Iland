package com.javarush.main.enums;

public enum TextMassages {
    STATISTIC_ON_BEGINNING_OF_GAME ("The statistic of animals and grass on the Island on the start of the game: "),
    NUMBER_OF_PREDATORS("The number of predators on the Island is: "),
    NUMBER_OF_HERBIVORES("The number of herbivores on the Island is: "),
    NUMBER_OF_GRASS("The number of plant on the Island is: "),
    NO_CHOOSING_DIRECTION ("The direction of moving wasn't been chosen. Please start again."),
    FAILURE_TO_CHOOSE_ACTION ("The action wasn't been chosen. Please start again."),
    FAILURE_TO_GET_MAX_NUMBER_ON_POSITION ("Failure to get the parameter \"maxNumberOnPosition\". "),
    FAILURE_TO_CREATE_INHABITANTS ("The inhabitants of the Island couldn't be created. Please stat again"),
    FAILURE_TO_LOAD_PROPERTIES ("Couldn't load Game Properties, please try again."),
    NO_PARAMETERS_FOUND ("The parameters of the animal were not found. "),
    VELCOM_MASSAGE ("Hello, player! \nDefault size of the Island is:\n%d - width, \n%d - length.\nWould you like to change it?\n"),
    NEW_SIZE_OF_ISLAND ("Now the size of the Island is:\n%d - width, \n%d - length.\nWould you like to change it again?\n"),
    OPTION_TO_CHANGE_ISLAND_SIZE ("Please type: yes or not."),
    ANSWER_YES ("Yes"),
    ANSWER_NOT ("Not"),
    WHAT_YOU_WANT_TO_CHANGE ("What do you want to change? \nPlease type: width or length."),
    LENGTH ("Length"),
    WIDTH ("Width"),
    TYPE_NEW_LENGTH ("Please, type new length from %d to %d including."),
    TYPE_NEW_WIDTH ("Please, type new width from %d to %d including.");

    private String massage;

    TextMassages(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
