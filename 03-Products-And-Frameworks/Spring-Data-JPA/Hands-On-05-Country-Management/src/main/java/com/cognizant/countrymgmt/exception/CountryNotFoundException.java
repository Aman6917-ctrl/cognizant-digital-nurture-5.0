package com.cognizant.countrymgmt.exception;

public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(String countryCode) {
        super("Country not found for code: " + countryCode);
    }
}
