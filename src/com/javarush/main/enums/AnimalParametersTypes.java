package com.javarush.main.enums;

public enum AnimalParametersTypes {
    WEIGHT ("weight"),
    MAX_NUMBER_ON_POSITION("maxNumberOnPosition"),
    MAX_TRAVEL_SPEED("maxTravelSpeed"),
    KG_FOR_FULL_SATURATION("kgForFullSaturation");

    String name;

    AnimalParametersTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
