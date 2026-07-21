package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:country-list.xml")
public class GetCountryByCodeApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetCountryByCodeApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Hands-On 10 - Get Country By Code");
        SpringApplication.run(GetCountryByCodeApplication.class, args);
    }
}
