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
@Order(8)
@Profile("!test")
public class HandsOn08UpdateCountryDemo implements ApplicationRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn08UpdateCountryDemo.class);

    private final CountryService countryService;

    public HandsOn08UpdateCountryDemo(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("=== Hands-On 8: updateCountry ===");
        if (countryService.findCountryByCodeOptional("CA").isPresent()) {
            Country updated = countryService.updateCountry("CA", "Canada (Updated Demo)");
            log.info("Updated: {}", updated);
        } else {
            log.warn("CA not in database — skip update demo");
        }
    }
}
