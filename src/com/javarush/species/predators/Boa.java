package com.javarush.species.predators;

import com.javarush.annotations.Predators;
import com.javarush.species.abstractclasses.Animal;

@Predators
public class Boa extends Animal {

    public Boa(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation);
    }
}
