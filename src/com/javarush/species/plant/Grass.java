package com.javarush.species.plant;

import com.javarush.annotations.Herb;
import com.javarush.species.abstractclasses.Plant;

@Herb
public class Grass extends Plant {

    public Grass(int weight, int maxNumberOnPosition) {
        super(weight, maxNumberOnPosition);
    }
}
