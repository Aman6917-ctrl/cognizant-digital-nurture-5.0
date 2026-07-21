package com.cognizant.springlearn.web;

import com.cognizant.springlearn.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/country")
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final Country country;

    @Autowired
    public CountryController(@Qualifier("country") Country country) {
        this.country = country;
    }

    @GetMapping
    public Country getCountry() {
        LOGGER.info("Returning country bean: code={}, name={}", country.getCode(), country.getName());
        return country;
    }
}
