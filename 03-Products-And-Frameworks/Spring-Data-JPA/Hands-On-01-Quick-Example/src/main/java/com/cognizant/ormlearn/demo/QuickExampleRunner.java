package com.cognizant.ormlearn.demo;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class QuickExampleRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(QuickExampleRunner.class);

    private final CountryService countryService;

    public QuickExampleRunner(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 1: Spring Data JPA Quick Example ===");
        for (Country country : countryService.findAll()) {
            System.out.println(country);
        }
        log.info("=== Quick example complete ===");
    }
}
