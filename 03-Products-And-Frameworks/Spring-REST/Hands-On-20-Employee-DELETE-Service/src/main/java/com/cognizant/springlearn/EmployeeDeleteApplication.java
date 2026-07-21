package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmployeeDeleteApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDeleteApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START Employee DELETE application bootstrap");
        SpringApplication.run(EmployeeDeleteApplication.class, args);
        LOGGER.info("END Employee DELETE application bootstrap");
    }
}
