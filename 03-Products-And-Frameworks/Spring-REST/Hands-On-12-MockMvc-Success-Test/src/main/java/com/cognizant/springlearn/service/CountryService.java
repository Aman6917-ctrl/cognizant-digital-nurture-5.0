package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private final Country country;

    @Autowired
    public CountryService(@Qualifier("country") Country country) {
        this.country = country;
    }

    public Optional<Country> getCountry(String code) {
        LOGGER.info("Service lookup for code={}", code);
        if (code == null || code.isBlank()) {
            return Optional.empty();
        }
        if (country.getCode() != null && country.getCode().equalsIgnoreCase(code.trim())) {
            return Optional.of(country);
        }
        return Optional.empty();
    }

    public Country getConfiguredCountry() {
        LOGGER.info("Returning configured country code={}", country.getCode());
        return country;
    }
}
