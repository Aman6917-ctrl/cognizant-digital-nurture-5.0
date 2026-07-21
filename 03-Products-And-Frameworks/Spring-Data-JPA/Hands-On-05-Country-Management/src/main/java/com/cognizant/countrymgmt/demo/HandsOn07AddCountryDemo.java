package com.cognizant.countrymgmt.demo;

import com.cognizant.countrymgmt.model.Country;
import com.cognizant.countrymgmt.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(7)
@Profile("!test")
public class HandsOn07AddCountryDemo implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn07AddCountryDemo.class);

    private static final String DEMO_CODE = "SG";

    private final CountryService countryService;

    public HandsOn07AddCountryDemo(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("=== Hands-On 7: addCountry ===");
        countryService.findCountryByCodeOptional(DEMO_CODE).ifPresentOrElse(
                existing -> log.info("Already present: {}", existing),
                () -> {
                    Country saved = countryService.addCountry(new Country(DEMO_CODE, "Singapore"));
                    log.info("Added: {}", saved);
                    log.info("Verified: {}", countryService.findCountryByCode(DEMO_CODE));
                });
    }
}
