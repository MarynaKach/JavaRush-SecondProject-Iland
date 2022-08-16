package com.javarush.main.species.abstractclasses;

import java.util.HashMap;

public abstract class Animal extends Entity {
   private double weight;
    private int maxNumberOnPosition;
    private int maxTravelSpeed;
    private double kgForFullSaturation;
    boolean isActionDone;
    private HashMap<String, Integer> eatingRation;
    private int saturationRatio;

    public Animal(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation, boolean isActionDone, HashMap<String, Integer> eatingRation, int saturationRatio) {
        this.weight = weight;
        this.maxNumberOnPosition = maxNumberOnPosition;
        this.maxTravelSpeed = maxTravelSpeed;
        this.kgForFullSaturation = kgForFullSaturation;
        this.isActionDone = isActionDone;
        this.eatingRation = eatingRation;
        this.saturationRatio = saturationRatio;
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

    public HashMap<String, Integer> getEatingRatio() {
        return eatingRation;
    }

    public int getSaturationRatio() {
        return saturationRatio;
    }

    public void setSaturationRatio(int saturationRatio) {
        this.saturationRatio = saturationRatio;
    }
}
