package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class CountryBeanDemoRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryBeanDemoRunner.class);

    @Override
    public void run(String... args) {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("country.xml")) {
            Country country = ctx.getBean("country", Country.class);
            LOGGER.debug("Loaded country bean from XML context");
            country.displayCountry();
        }
    }
}
