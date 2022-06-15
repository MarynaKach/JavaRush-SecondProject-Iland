package com.javarush.species.abstractclasses;

public class Species {
        int [][] position;

        public Species(int[][] position) {
            this.position = position;
        }

        public int[][] getPosition() {
            return position;
        }

        public void setPosition(int[][] position) {
            this.position = position;
        }
}
