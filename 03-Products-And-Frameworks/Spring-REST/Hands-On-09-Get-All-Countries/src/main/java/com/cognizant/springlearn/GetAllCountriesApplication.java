package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:country-list.xml")
public class GetAllCountriesApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetAllCountriesApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Hands-On 09 - Get All Countries");
        SpringApplication.run(GetAllCountriesApplication.class, args);
    }
}
