package com.javarush.main.species.herbivores;

import com.javarush.main.annotations.Herbivores;
import com.javarush.main.species.abstractclasses.Animal;

import java.io.Serializable;
import java.util.HashMap;

@Herbivores
public class Boar extends Animal implements Serializable {

    public Boar(double weight, int maxNumberOnPosition, int maxTravelSpeed, double kgForFullSaturation,
                boolean isActionDone, HashMap<String, Integer> eatingRation, int saturationRatio) {
        super(weight, maxNumberOnPosition, maxTravelSpeed, kgForFullSaturation, isActionDone, eatingRation, saturationRatio);
    }

    /*@Override
    public void setActionDone(boolean actionDone) {
        super.setActionDone(actionDone);
    }

    @Override
    public void setSaturationRatio(int saturationRatio) {
        super.setSaturationRatio(saturationRatio);
    }*/
}
