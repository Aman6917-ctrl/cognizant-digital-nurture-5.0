package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringWebApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringWebApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Spring Boot web application (embedded Tomcat)");
        SpringApplication.run(SpringWebApplication.class, args);
        LOGGER.info("Application started successfully");
    }
}
