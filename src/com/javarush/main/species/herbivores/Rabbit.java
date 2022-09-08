package com.javarush.main.species.herbivores;

import com.javarush.main.species.abstractclasses.Animal;

import java.util.HashMap;

public class Rabbit extends Animal {

    public Rabbit(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation,
                  boolean isActionDone, HashMap<String, Integer> eatingRation, int saturationRatio) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, isActionDone, eatingRation, saturationRatio);
    }
}
