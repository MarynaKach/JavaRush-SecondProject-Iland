package com.javarush.main.species.predators;

import com.javarush.main.species.abstractclasses.Animal;

import java.util.HashMap;

public class Boa extends Animal {

    public Boa(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation,
               boolean isActionDone, HashMap<String, Integer> eatingRation, int saturationRatio) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, isActionDone, eatingRation, saturationRatio);
    }
}
