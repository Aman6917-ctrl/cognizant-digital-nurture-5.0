package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoggingDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingDemoApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting logging demo");
        SpringApplication.run(LoggingDemoApplication.class, args);
    }
}
