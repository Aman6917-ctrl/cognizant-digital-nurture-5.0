package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Country;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class CountryDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDao.class);

    private static List<Country> COUNTRY_LIST = new ArrayList<>();

    public CountryDao() {
        LOGGER.info("START CountryDao constructor - loading country-list.xml");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country-list.xml")) {
            @SuppressWarnings("unchecked")
            List<Country> loaded = (List<Country>) context.getBean("countryList");
            COUNTRY_LIST = new ArrayList<>(loaded);
            LOGGER.info("Loaded {} countries from XML", COUNTRY_LIST.size());
        }
        LOGGER.info("END CountryDao constructor");
    }

    public Country addCountry(Country country) {
        LOGGER.info("START addCountry DAO code={}", country.getCode());
        COUNTRY_LIST.add(country);
        LOGGER.debug("Country list size after add: {}", COUNTRY_LIST.size());
        LOGGER.info("END addCountry DAO");
        return country;
    }

    public List<Country> getAllCountries() {
        LOGGER.info("START getAllCountries DAO");
        List<Country> countries = Collections.unmodifiableList(new ArrayList<>(COUNTRY_LIST));
        LOGGER.info("END getAllCountries DAO count={}", countries.size());
        return countries;
    }
}
