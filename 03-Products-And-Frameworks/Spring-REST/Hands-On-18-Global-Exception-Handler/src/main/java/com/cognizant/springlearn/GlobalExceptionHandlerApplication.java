package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GlobalExceptionHandlerApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandlerApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START Global Exception Handler application bootstrap");
        SpringApplication.run(GlobalExceptionHandlerApplication.class, args);
        LOGGER.info("END Global Exception Handler application bootstrap");
    }
}
