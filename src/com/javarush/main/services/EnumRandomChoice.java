package com.javarush.main.services;

import java.util.Random;

public class EnumRandomChoice {

    protected  <T extends Enum<?>> T chooseRandomEnum (Class<T> clazz) {
        Random random = new Random();
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];

    }

}
