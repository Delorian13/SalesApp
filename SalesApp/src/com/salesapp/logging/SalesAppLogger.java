package com.salesapp.logging;

import java.io.IOException;
import java.util.logging.*;

public class SalesAppLogger {
    private static Logger logger;

    static {
        try {
            LogManager.getLogManager().readConfiguration();
            logger = Logger.getLogger("com.salesapp");

            FileHandler fileHandler = new FileHandler("sales_app.log", true);
            fileHandler.setFormatter(new SimpleFormatter());

            logger.addHandler(fileHandler);
            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to set up logger: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}

