package com.javarush.main.species.predators;

import com.javarush.main.annotations.Predators;
import com.javarush.main.species.abstractclasses.Animal;

import java.io.Serializable;
import java.util.HashMap;

@Predators
public class Boa extends Animal implements Serializable {

    public Boa(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation,
               boolean isActionDone, HashMap<String, Integer> eatingRation, int saturationRatio) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, isActionDone, eatingRation, saturationRatio);
    }
}
