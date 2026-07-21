package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    public Country() {
        LOGGER.debug("Country bean constructed");
    }

    public String getCode() {
        LOGGER.debug("getCode() invoked, value={}", code);
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("setCode() invoked, value={}", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("getName() invoked, value={}", name);
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("setName() invoked, value={}", name);
        this.name = name;
    }

    public void displayCountry() {
        LOGGER.info("Country: code={}, name={}", code, name);
    }
}
