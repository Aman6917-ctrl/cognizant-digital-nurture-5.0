package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountryValidationApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryValidationApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START Country Validation application bootstrap");
        SpringApplication.run(CountryValidationApplication.class, args);
        LOGGER.info("END Country Validation application bootstrap");
    }
}
