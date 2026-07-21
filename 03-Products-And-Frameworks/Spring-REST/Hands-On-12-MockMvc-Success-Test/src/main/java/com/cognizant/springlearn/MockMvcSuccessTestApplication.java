package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource("classpath:country.xml")
public class MockMvcSuccessTestApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(MockMvcSuccessTestApplication.class);

    public static void main(String[] args) {
        LOGGER.info("Starting Hands-On 12 - MockMvc Success Test");
        SpringApplication.run(MockMvcSuccessTestApplication.class, args);
    }
}
