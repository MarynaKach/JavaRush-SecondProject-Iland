package com.javarush.main.species.herbivores;

import com.javarush.main.annotations.Herbivores;
import com.javarush.main.species.abstractclasses.Animal;

import java.io.Serializable;
import java.util.HashMap;

@Herbivores
public class Deer extends Animal implements Serializable {

    public Deer(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation,
                boolean isActionDone, HashMap<String, Integer> eatingRation, int saturationRatio) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, isActionDone, eatingRation, saturationRatio);
    }
}
