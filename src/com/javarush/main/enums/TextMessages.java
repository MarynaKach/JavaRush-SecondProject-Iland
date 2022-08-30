package com.javarush.main.enums;

public enum TextMessages {
    STATISTIC_ON_BEGINNING_OF_GAME("The statistic of animals and grass on the Island on the start of the game: "),
    STATISTIC_ON_BEGINNING_OF_DAY("The statistic of animals and grass on the Island on the beginning of the day %d: \n"),
    NUMBER_OF_PREDATORS("The number of predators on the Island is: "),
    NUMBER_OF_HERBIVORES("The number of herbivores on the Island is: "),
    NUMBER_OF_GRASS("The number of plant on the Island is: "),
    NO_CHOOSING_DIRECTION("The direction of moving wasn't been chosen. Please start again."),
    FAILURE_TO_CHOOSE_ACTION("The action wasn't been chosen. Please start again."),
    FAILURE_TO_CREATE_INHABITANTS("The inhabitants of the Island couldn't be created. Please stat again"),
    FAILURE_TO_LOAD_PROPERTIES("Couldn't load GameApplication Properties, please try again."),
    WELCOME_MASSAGE("Hello, player! \nDefault size of the Island is:\n%d - width, \n%d - length.\nWould you like to change it?\n"),
    NEW_SIZE_OF_ISLAND("Now the size of the Island is:\n%d - width, \n%d - length.\nTHE GAME STARTS!\n"),
    OPTION_TO_CHANGE_ISLAND_SIZE("Please type: yes or not."),
    ANSWER_YES("Yes"),
    ANSWER_NOT("Not"),
    TYPE_NEW_LENGTH("Please, type new length starting from  %d.\n"),
    TYPE_NEW_WIDTH("Please, type new width starting from %d.\n"),
    FAILURE_TO_JOIN_THREAD ("Main thread failed to join other threads.");

    private String massage;

    TextMessages(String massage) {
        this.massage = massage;
    }

    public String getMassage() {
        return massage;
    }
}
