package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountryPostApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryPostApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START Country POST application bootstrap");
        SpringApplication.run(CountryPostApplication.class, args);
        LOGGER.info("END Country POST application bootstrap");
    }
}
