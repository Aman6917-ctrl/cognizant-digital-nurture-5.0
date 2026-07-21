package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountryBeanDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryBeanDemoApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Country bean demo");
        SpringApplication.run(CountryBeanDemoApplication.class, args);
    }
}
