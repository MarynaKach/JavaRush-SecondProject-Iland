package com.javarush.main.enums;

import com.javarush.main.species.herbivores.*;
import com.javarush.main.species.plant.Grass;
import com.javarush.main.species.predators.Boa;
import com.javarush.main.species.predators.Eagle;
import com.javarush.main.species.predators.Fox;
import com.javarush.main.species.predators.Wolf;

public enum AnimalEnum {
    BOAR("Boar", Boar.class),
    BUFFALO("Buffalo", Buffalo.class),
    CATERPILLAR("Caterpillar", Caterpillar.class),
    DEER("Deer", Deer.class),
    DUCK("Duck", Duck.class),
    GOAT("Goat", Goat.class),
    HORSE("Horse", Horse.class),
    MOUSE("Mouse", Mouse.class),
    RABBIT("Rabbit", Rabbit.class),
    SHIP("Ship", Ship.class),
    BEAR("Bear", Boar.class),
    BOA("Boa", Boa.class),
    EAGLE("Eagle", Eagle.class),
    FOX("Fox", Fox.class),
    WOLF("Wolf", Wolf.class),
    GRASS("Grass", Grass.class);

    String name;
    Class clazz;

    AnimalEnum(String name, Class clazz) {
        this.name = name;
        this.clazz = clazz;
    }

    public Class getClazz() {
        return clazz;
    }

    public String getName() {
        return name;
    }
}
