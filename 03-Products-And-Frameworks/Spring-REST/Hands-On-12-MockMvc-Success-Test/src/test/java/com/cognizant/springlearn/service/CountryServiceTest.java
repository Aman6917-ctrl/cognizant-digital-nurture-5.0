package com.cognizant.springlearn.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.cognizant.springlearn.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    void getCountryFindsIndiaCaseInsensitive() {
        Country country = countryService.getCountry("in").orElseThrow();
        assertThat(country.getCode()).isEqualTo("IN");
        assertThat(country.getName()).isEqualTo("India");
    }

    @Test
    void getConfiguredCountryReturnsXmlBean() {
        Country country = countryService.getConfiguredCountry();
        assertThat(country.getCode()).isEqualTo("IN");
        assertThat(country.getName()).isEqualTo("India");
    }
}
