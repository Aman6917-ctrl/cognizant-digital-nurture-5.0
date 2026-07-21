package com.cognizant.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AppenderLoggingApp {

    private static final Logger logger = LoggerFactory.getLogger(AppenderLoggingApp.class);

    public static void main(String[] args) {
        logger.trace("Trace detail: entering main");
        logger.debug("Debug detail: configuration loaded");
        logger.info("Info: application started");
        logger.warn("Warn: disk space below 10%");
        logger.error("Critical failure in batch job");
    }
}
