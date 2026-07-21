package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DateFormatDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateFormatDemoApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting DateFormat bean demo");
        SpringApplication.run(DateFormatDemoApplication.class, args);
    }
}
