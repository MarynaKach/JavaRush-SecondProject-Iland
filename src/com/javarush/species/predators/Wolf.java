package com.javarush.species.predators;

import com.javarush.annotations.Predators;
import com.javarush.species.abstractclasses.Animal;

@Predators
public class Wolf extends Animal {
    public Wolf(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation);
    }
}
