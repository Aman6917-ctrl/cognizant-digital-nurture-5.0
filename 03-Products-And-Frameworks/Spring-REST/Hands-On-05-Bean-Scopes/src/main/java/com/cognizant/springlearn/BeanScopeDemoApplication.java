package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BeanScopeDemoApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(BeanScopeDemoApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting bean scope demo");
        SpringApplication.run(BeanScopeDemoApplication.class, args);
    }
}
