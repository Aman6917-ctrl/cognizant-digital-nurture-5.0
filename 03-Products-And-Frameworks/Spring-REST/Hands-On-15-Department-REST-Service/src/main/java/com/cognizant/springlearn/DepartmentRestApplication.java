package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DepartmentRestApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentRestApplication.class);

    public static void main(String[] args) {
        LOGGER.info("START Department REST application bootstrap");
        SpringApplication.run(DepartmentRestApplication.class, args);
        LOGGER.info("END Department REST application bootstrap");
    }
}
