package com.javarush.main.species.abstractclasses;

public abstract class Animal extends Entity {
   private double weight;
    private int maxNumberOnPosition;
    private int maxTravelSpeed;
    private double kgForFullSaturation;
    boolean isActionDone;


    public Animal(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation, boolean isActionDone) {
        this.weight = weight;
        this.maxNumberOnPosition = maxNumberOnPosition;
        this.maxTravelSpeed = maxTravelSpeed;
        this.kgForFullSaturation = kgForFullSaturation;
        this.isActionDone = isActionDone;
    }

    public double getWeight() {
        return weight;
    }

    public int getMaxNumberOnPosition() {
        return maxNumberOnPosition;
    }

    public int getMaxTravelSpeed() {
        return maxTravelSpeed;
    }

    public double getKgForFullSaturation() {
        return kgForFullSaturation;
    }

    public boolean isActionDone() {
        return isActionDone;
    }

    public void setActionDone(boolean actionDone) {
        isActionDone = actionDone;
    }
}
