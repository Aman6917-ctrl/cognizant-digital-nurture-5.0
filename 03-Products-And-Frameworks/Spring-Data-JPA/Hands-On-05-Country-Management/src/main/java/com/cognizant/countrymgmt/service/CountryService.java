package com.cognizant.countrymgmt.service;

import com.cognizant.countrymgmt.exception.CountryNotFoundException;
import com.cognizant.countrymgmt.model.Country;
import com.cognizant.countrymgmt.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryService.class);

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<Country> getAllCountries() {
        log.info("Fetching all countries");
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Country findCountryByCode(String countryCode) {
        log.info("Finding country by code: {}", countryCode);
        return countryRepository.findByCountryCode(countryCode)
                .orElseThrow(() -> new CountryNotFoundException(countryCode));
    }

    @Transactional(readOnly = true)
    public Optional<Country> findCountryByCodeOptional(String countryCode) {
        return countryRepository.findByCountryCode(countryCode);
    }

    @Transactional(readOnly = true)
    public List<Country> findByPartialName(String namePart) {
        log.info("Searching countries with name containing: {}", namePart);
        return countryRepository.findByCountryNameContainingIgnoreCase(namePart);
    }

    @Transactional
    public Country addCountry(Country country) {
        log.info("Adding country: {}", country);
        return countryRepository.save(country);
    }

    @Transactional
    public Country updateCountry(String countryCode, String newCountryName) {
        log.info("Updating country {} to name {}", countryCode, newCountryName);
        Country country = findCountryByCode(countryCode);
        country.setCountryName(newCountryName);
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String countryCode) {
        log.info("Deleting country with code: {}", countryCode);
        Country country = findCountryByCode(countryCode);
        countryRepository.delete(country);
    }
}
