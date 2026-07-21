package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmployeePutApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeePutApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START Employee PUT application bootstrap");
        SpringApplication.run(EmployeePutApplication.class, args);
        LOGGER.info("END Employee PUT application bootstrap");
    }
}
