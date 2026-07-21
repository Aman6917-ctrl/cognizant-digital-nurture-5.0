package com.cognizant.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParameterizedLoggingApp {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingApp.class);

    public static void main(String[] args) {
        String username = "alice";
        int age = 29;

        logger.info("User {} logged in", username);
        logger.debug("Age {}", age);
        logger.warn("Session expiring in {} seconds for user {}", 120, username);
        logger.error("Failed to save profile for user {}", username);
    }
}
