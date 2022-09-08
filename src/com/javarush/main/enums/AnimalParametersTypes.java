package com.javarush.main.enums;

public enum AnimalParametersTypes {
    WEIGHT("weight"),
    MAX_NUMBER_ON_POSITION("maxNumberOnPosition"),
    MAX_TRAVEL_SPEED("maxTravelSpeed"),
    KG_FOR_FULL_SATURATION("kgForFullSaturation"),
    POSSIBILITY_TO_EAT("possibilityToEat"),
    SATURATION_RATIO("saturationRatio"),
    WIDTH("width"),
    LENGTH("length"),
    DAYS_GAME_LAST("daysGameLasts"),
    MIN_LIMIT_WIDTH("minLimitWidth"),
    MIN_LIMIT_LENGTH("minLimitLength"),
    PLANT_GROWTH_RATIO("plantGrowthRatio");

    private String name;

    AnimalParametersTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
