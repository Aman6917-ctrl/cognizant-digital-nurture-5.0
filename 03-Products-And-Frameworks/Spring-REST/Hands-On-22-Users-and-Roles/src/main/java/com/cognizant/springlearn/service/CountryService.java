package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    public List<Country> getAllCountries() {
        LOGGER.info("START getAllCountries service");
        List<Country> countries = List.of(new Country("IN", "India"), new Country("JP", "Japan"));
        LOGGER.info("END getAllCountries service");
        return countries;
    }
}
