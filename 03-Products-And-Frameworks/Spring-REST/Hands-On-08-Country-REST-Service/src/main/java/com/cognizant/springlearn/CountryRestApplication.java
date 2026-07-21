package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:country.xml")
public class CountryRestApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRestApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Hands-On 08 - Country REST Service");
        SpringApplication.run(CountryRestApplication.class, args);
    }
}
