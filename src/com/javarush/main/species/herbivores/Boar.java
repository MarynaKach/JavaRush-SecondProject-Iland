package com.javarush.main.species.herbivores;

import com.javarush.main.annotations.Herbivores;
import com.javarush.main.species.abstractclasses.Animal;

@Herbivores
public class Boar extends Animal {
    /*private double weight;
    private int maxNumberOnPosition;
    private int maxTravelSpeed;
    private double kgForFullSaturation;

    public Boar (double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation) {
        this.weight = weight;
        this.maxNumberOnPosition = maxNumberOnPosition;
        this.maxTravelSpeed = maxTravelSpeed;
        this.kgForFullSaturation = kgForFullSaturation;

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
    }*/

    public Boar(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation, boolean ifActionDone) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, ifActionDone);
    }

    @Override
    public void setActionDone(boolean actionDone) {
        super.setActionDone(actionDone);
    }
}
