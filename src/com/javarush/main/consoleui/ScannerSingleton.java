package com.javarush.main.consoleui;

import java.util.Scanner;

public class ScannerSingleton {
    private static Scanner instance = new Scanner(System.in);

    public static Scanner getInstance() {
        return instance;
    }
}
