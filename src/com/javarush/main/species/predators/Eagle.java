package com.javarush.main.species.predators;

import com.javarush.main.annotations.Predators;
import com.javarush.main.species.abstractclasses.Animal;

import java.io.Serializable;

@Predators
public class Eagle extends Animal implements Serializable {
    /*private double weight;
    private int maxNumberOnPosition;
    private int maxTravelSpeed;
    private double kgForFullSaturation;

    public Eagle (double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation) {
        this.weight = weight;
        this.maxNumberOnPosition = maxNumberOnPosition;
        this.maxTravelSpeed = maxTravelSpeed;
        this.kgForFullSaturation = kgForFullSaturation;

    }*/

    public Eagle(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation, boolean ifActionDone) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, ifActionDone);
    }
}
