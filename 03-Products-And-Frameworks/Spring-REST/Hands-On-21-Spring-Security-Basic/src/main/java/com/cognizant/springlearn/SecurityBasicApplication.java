package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecurityBasicApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityBasicApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START Spring Security Basic application");
        SpringApplication.run(SecurityBasicApplication.class, args);
        LOGGER.info("END Spring Security Basic application bootstrap");
    }
}
