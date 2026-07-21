package com.cognizant.countrymgmt.demo;

import com.cognizant.countrymgmt.exception.CountryNotFoundException;
import com.cognizant.countrymgmt.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(6)
@Profile("!test")
public class HandsOn06FindCountryDemo implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn06FindCountryDemo.class);

    private final CountryService countryService;

    public HandsOn06FindCountryDemo(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("=== Hands-On 6: findCountryByCode ===");
        log.info("Found: {}", countryService.findCountryByCode("IN"));

        try {
            countryService.findCountryByCode("XX");
        } catch (CountryNotFoundException ex) {
            log.warn("Expected not found: {}", ex.getMessage());
        }
    }
}
