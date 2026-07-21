package com.cognizant.springlearn.service;

import com.cognizant.springlearn.Country;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
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

    public Optional<Country> getCountry(String code) {
        LOGGER.info("Looking up country by code={}", code);
        if (code == null) {
            return Optional.empty();
        }
        String normalized = code.trim().toUpperCase(Locale.ROOT);
        return countries.stream()
                .filter(c -> c.getCode() != null && c.getCode().equalsIgnoreCase(normalized))
                .findFirst();
    }
}
