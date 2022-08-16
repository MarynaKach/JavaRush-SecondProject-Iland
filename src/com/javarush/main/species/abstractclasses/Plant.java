package com.javarush.main.species.abstractclasses;

public abstract class Plant extends Entity {
    private int weight;
    private int maxNumberOnPosition;

    public Plant(int weight, int maxNumberOnPosition) {
        this.weight = weight;
        this.maxNumberOnPosition = maxNumberOnPosition;
    }

    public int getWeight() {
        return weight;
    }

    public int getMaxNumberOnPosition() {
        return maxNumberOnPosition;
    }
}
