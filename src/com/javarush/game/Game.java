package com.javarush.game;

import com.javarush.consoleui.ConsoleDialogue;

import java.lang.reflect.InvocationTargetException;

public class Game {
    public static void main(String[] args) {
        StartGame startGame = new StartGame();
        startGame.play();

       /* ConsoleDialogue consoleDialogue = new ConsoleDialogue();
        consoleDialogue.start();
        int x = 100;
        int y = 20;
        for (int i = 0; i < x; i++) {

        }*/
    }
}
