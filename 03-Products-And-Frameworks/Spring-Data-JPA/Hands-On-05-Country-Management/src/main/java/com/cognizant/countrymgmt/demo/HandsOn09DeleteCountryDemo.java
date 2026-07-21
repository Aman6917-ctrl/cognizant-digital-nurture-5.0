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
@Order(9)
@Profile("!test")
public class HandsOn09DeleteCountryDemo implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn09DeleteCountryDemo.class);

    private static final String DEMO_CODE = "SG";

    private final CountryService countryService;

    public HandsOn09DeleteCountryDemo(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("=== Hands-On 9: deleteCountry ===");
        if (countryService.findCountryByCodeOptional(DEMO_CODE).isPresent()) {
            countryService.deleteCountry(DEMO_CODE);
            log.info("Deleted country code {}", DEMO_CODE);
            log.info("Lookup after delete present? {}", countryService.findCountryByCodeOptional(DEMO_CODE).isPresent());
        } else {
            log.info("Country {} not found — nothing to delete (HO7 may not have run yet)", DEMO_CODE);
        }
    }
}
