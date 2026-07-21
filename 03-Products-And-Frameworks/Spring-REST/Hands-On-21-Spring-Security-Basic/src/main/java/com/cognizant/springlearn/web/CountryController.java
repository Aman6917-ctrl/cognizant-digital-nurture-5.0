package com.cognizant.springlearn.web;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/countries")
    public List<Country> getCountries() {
        LOGGER.info("START GET /countries controller");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("END GET /countries controller");
        return countries;
    }
}
