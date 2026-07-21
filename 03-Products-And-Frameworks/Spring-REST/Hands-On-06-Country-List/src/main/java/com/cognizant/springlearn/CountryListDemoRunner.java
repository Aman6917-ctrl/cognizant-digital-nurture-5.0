package com.cognizant.springlearn;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class CountryListDemoRunner implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryListDemoRunner.class);

    @Override
    @SuppressWarnings("unchecked")
    public void run(String... args) {
        try (ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("country-list.xml")) {
            List<Country> countries = (List<Country>) ctx.getBean("countryList");
            LOGGER.debug("Loaded {} countries from XML", countries.size());
            for (Country country : countries) {
                country.displayCountry();
            }
        }
    }
}
