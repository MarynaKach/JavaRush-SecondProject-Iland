package com.javarush.main.species.herbivores;

import com.javarush.main.annotations.Herbivores;
import com.javarush.main.species.abstractclasses.Animal;

import java.io.Serializable;

@Herbivores
public class Buffalo extends Animal implements Serializable {
    /*private double weight;
    private int maxNumberOnPosition;
    private int maxTravelSpeed;
    private double kgForFullSaturation;

    public Buffalo (double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation) {
        this.weight = weight;
        this.maxNumberOnPosition = maxNumberOnPosition;
        this.maxTravelSpeed = maxTravelSpeed;
        this.kgForFullSaturation = kgForFullSaturation;

    }*/


    public Buffalo(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation, boolean ifActionDone) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, ifActionDone);
    }
}
