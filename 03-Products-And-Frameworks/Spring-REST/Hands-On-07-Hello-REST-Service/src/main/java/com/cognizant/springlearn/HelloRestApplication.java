package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloRestApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloRestApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Hands-On 07 - Hello REST Service");
        SpringApplication.run(HelloRestApplication.class, args);
    }
}
