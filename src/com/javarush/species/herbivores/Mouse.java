package com.javarush.species.herbivores;


import com.javarush.annotations.Herbivores;
import com.javarush.species.abstractclasses.Animal;

@Herbivores
public class Mouse extends Animal {
    public Mouse(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation);
    }
}
