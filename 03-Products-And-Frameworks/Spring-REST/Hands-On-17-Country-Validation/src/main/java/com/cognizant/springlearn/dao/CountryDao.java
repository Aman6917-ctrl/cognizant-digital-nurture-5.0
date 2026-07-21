package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Country;
import java.util.ArrayList;
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
        LOGGER.info("START CountryDao constructor");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country-list.xml")) {
            @SuppressWarnings("unchecked")
            List<Country> loaded = (List<Country>) context.getBean("countryList");
            COUNTRY_LIST = new ArrayList<>(loaded);
        }
        LOGGER.info("END CountryDao constructor size={}", COUNTRY_LIST.size());
    }

    public Country addCountry(Country country) {
        LOGGER.info("START addCountry DAO");
        COUNTRY_LIST.add(country);
        LOGGER.info("END addCountry DAO");
        return country;
    }
}
