package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Country;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDao.class);

    private final List<Country> countries = new ArrayList<>();

    public Country addCountry(Country country) {
        LOGGER.info("START addCountry DAO");
        countries.add(country);
        LOGGER.info("END addCountry DAO");
        return country;
    }
}
