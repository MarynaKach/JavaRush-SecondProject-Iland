package com.javarush.species.abstractclasses;

public class Animal extends Species {
    int weight;
    int movingSpeed;
    int kgForSaturation;

    protected Animal(int[][] position) {
        super(position);
    }

    protected Animal(int x, int y) {
        super(new int[][]{{x, y}});
    }

    public void chooseDirection() {
        System.out.println("The Animal chose the direction.");
    }

    public void move() {
        System.out.println("The Animal made its move.");
    }

    public void eat() {
        System.out.println("The Animal eat.");
    }

    public void reproduction() {
        System.out.println("The Animal gave birth.");
    }

    public void die() {
        System.out.println("The Animal died.");
    }
}
