package com.javarush.species.herbivores;

import com.javarush.species.abstractclasses.Herbivores;

public class Goat extends Herbivores {
    public Goat(int[][] position) {
        super(position);
    }

    public Goat(int x, int y) {
        super(x, y);
    }
}
