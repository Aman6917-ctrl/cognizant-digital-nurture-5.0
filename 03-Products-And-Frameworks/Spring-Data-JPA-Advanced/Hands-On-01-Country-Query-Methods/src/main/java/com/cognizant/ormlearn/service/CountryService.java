package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryService.class);

    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<Country> findByNameContaining(String fragment) {
        log.info("Query: findByNameContaining('{}')", fragment);
        return countryRepository.findByNameContaining(fragment);
    }

    @Transactional(readOnly = true)
    public List<Country> findByNameContainingOrderByNameAsc(String fragment) {
        log.info("Query: findByNameContainingOrderByNameAsc('{}')", fragment);
        return countryRepository.findByNameContainingOrderByNameAsc(fragment);
    }

    @Transactional(readOnly = true)
    public List<Country> findByNameStartingWith(String prefix) {
        log.info("Query: findByNameStartingWith('{}')", prefix);
        return countryRepository.findByNameStartingWith(prefix);
    }
}
