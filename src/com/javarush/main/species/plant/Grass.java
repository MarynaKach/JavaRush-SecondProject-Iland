package com.javarush.main.species.plant;

import com.javarush.main.annotations.Herb;
import com.javarush.main.species.abstractclasses.Plant;

@Herb
public class Grass extends Plant {

    public Grass(int weight, int maxNumberOnPosition) {
        super(weight, maxNumberOnPosition);
    }
}
