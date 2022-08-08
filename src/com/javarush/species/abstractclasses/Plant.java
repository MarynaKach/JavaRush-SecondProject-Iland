package com.javarush.species.abstractclasses;

public abstract class Plant extends Entity {
    private int weight;
    private int maxNumberOnPosition;

    public Plant(int weight, int maxNumberOnPosition) {
        this.weight = weight;
        this.maxNumberOnPosition = maxNumberOnPosition;
    }
}
