package com.epicode.flowershop.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomLogger extends Logger {

    protected CustomLogger(String name, String resourceBundleName) {
        super(name, resourceBundleName);
    }

    public void info(String msg) {
        System.out.println(msg);
        log(Level.INFO, msg);
    }

    public void error(String msg) {
        System.err.println(msg);
        log(Level.SEVERE, msg);
    }
}
