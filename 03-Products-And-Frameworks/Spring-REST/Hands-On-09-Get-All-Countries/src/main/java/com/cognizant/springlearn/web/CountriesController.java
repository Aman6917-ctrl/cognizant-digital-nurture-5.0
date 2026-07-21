package com.cognizant.springlearn.web;

import com.cognizant.springlearn.Country;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountriesController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountriesController.class);

    private final List<Country> countries;

    @Autowired
    public CountriesController(@Qualifier("countryList") List<Country> countries) {
        this.countries = countries;
    }

    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("Returning all countries, count={}", countries.size());
        return countries;
    }
}
