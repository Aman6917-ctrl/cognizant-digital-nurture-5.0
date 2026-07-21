package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.CountryNotFoundException;
import java.util.List;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private final List<Country> countries;

    @Autowired
    public CountryService(@Qualifier("countryList") List<Country> countries) {
        this.countries = countries;
    }

    public Country getCountry(String code) {
        LOGGER.info("Looking up country by code={}", code);
        if (code == null || code.isBlank()) {
            throw new CountryNotFoundException(String.valueOf(code));
        }
        String normalized = code.trim().toUpperCase(Locale.ROOT);
        return countries.stream()
                .filter(c -> c.getCode() != null && c.getCode().equalsIgnoreCase(normalized))
                .findFirst()
                .orElseThrow(() -> {
                    LOGGER.info("Country not found for code={}", code);
                    return new CountryNotFoundException(code);
                });
    }
}
