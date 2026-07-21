package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JwtAuthorizationFilterApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthorizationFilterApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START JWT Authorization Filter application");
        SpringApplication.run(JwtAuthorizationFilterApplication.class, args);
    }
}
