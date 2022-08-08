package com.javarush.species.abstractclasses;

public abstract class Animal extends Entity {
    private double weight;
    private int maxNumberOnPosition;
    private int maxTravelSpeed;
    private double kgForFullSaturation;

    public Animal(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation) {
        this.weight = weight;
        this.maxNumberOnPosition = maxNumberOnPosition;
        this.maxTravelSpeed = maxTravelSpeed;
        this.kgForFullSaturation = kgForFullSaturation;

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
