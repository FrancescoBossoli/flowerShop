package com.epicode.flowershop;

import com.epicode.flowershop.core.ApplicationMenu;

public class Main {

    private static final ApplicationMenu menu = ApplicationMenu.getInstance();

    public static void main(String[] args) {
        systemStart();
    }

    public static void systemStart() {
        clearConsole();
        menu.startEmulationSystem();
    }

    public static void clearConsole() {
        for (int i = 0; i < 10; ++i) System.out.println();
    }

}