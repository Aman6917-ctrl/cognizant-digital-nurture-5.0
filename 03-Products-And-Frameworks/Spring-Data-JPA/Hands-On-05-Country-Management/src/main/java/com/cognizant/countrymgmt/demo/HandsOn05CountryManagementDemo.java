package com.cognizant.countrymgmt.demo;

import com.cognizant.countrymgmt.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(5)
@Profile("!test")
public class HandsOn05CountryManagementDemo implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn05CountryManagementDemo.class);

    private final CountryService countryService;

    public HandsOn05CountryManagementDemo(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("=== Hands-On 5: List all countries & query methods ===");
        countryService.getAllCountries().forEach(c -> log.info("  {}", c));
        log.info("Partial name 'united':");
        countryService.findByPartialName("united").forEach(c -> log.info("  {}", c));
    }
}
