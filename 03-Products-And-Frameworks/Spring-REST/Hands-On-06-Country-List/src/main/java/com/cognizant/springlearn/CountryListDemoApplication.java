package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountryListDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryListDemoApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting country list demo");
        SpringApplication.run(CountryListDemoApplication.class, args);
    }
}
