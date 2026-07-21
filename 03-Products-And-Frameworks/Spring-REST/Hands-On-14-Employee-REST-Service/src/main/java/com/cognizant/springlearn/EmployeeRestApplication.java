package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmployeeRestApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeRestApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START Employee REST application bootstrap");
        SpringApplication.run(EmployeeRestApplication.class, args);
        LOGGER.info("END Employee REST application bootstrap");
    }
}
