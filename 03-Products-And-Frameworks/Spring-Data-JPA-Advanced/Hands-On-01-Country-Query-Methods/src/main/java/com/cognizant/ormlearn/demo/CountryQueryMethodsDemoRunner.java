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
public class CountryQueryMethodsDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CountryQueryMethodsDemoRunner.class);

    private final CountryService countryService;

    public CountryQueryMethodsDemoRunner(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 1: Country Query Methods ===");

        print("findByNameContaining('an')", countryService.findByNameContaining("an"));
        print("findByNameContainingOrderByNameAsc('a')", countryService.findByNameContainingOrderByNameAsc("a"));
        print("findByNameStartingWith('U')", countryService.findByNameStartingWith("U"));

        log.info("=== Hands-On 1 complete ===");
    }

    private void print(String label, Iterable<Country> countries) {
        System.out.println("-- " + label + " --");
        countries.forEach(System.out::println);
    }
}
