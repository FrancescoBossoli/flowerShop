package com.epicode.flowershop.utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.SimpleFormatter;

public class UniversalLogger {
    private static UniversalLogger instance;
    private static final CustomLogger logger = new CustomLogger("GlobalLogger", null);

    public static synchronized UniversalLogger getInstance() {
        if (instance == null) instance = new UniversalLogger();
        return instance;
    }

    public CustomLogger getGlobalLogger() {
        return logger;
    }

    private UniversalLogger() {
        try {
            FileHandler fileHandler = new FileHandler("logs.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            System.err.println("Logger error: " + e.getMessage());
        }
    }


}
