package com.cognizant.ormcompare.springdata.service;

import com.cognizant.ormcompare.springdata.model.Country;
import com.cognizant.ormcompare.springdata.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SpringDataCountryService {

    private static final Logger log = LoggerFactory.getLogger(SpringDataCountryService.class);

    private final CountryRepository countryRepository;

    public SpringDataCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<Country> findAll() {
        log.info("[Spring Data JPA] findAll via JpaRepository");
        return countryRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Country> findByCode(String code) {
        log.info("[Spring Data JPA] findByCountryCode({})", code);
        return countryRepository.findByCountryCode(code);
    }

    @Transactional
    public Country save(Country country) {
        log.info("[Spring Data JPA] save {}", country);
        return countryRepository.save(country);
    }

    @Transactional
    public Country update(Long id, String newName) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Country not found: " + id));
        country.setCountryName(newName);
        log.info("[Spring Data JPA] update id {} name to {}", id, newName);
        return countryRepository.save(country);
    }

    @Transactional
    public void deleteById(Long id) {
        log.info("[Spring Data JPA] deleteById {}", id);
        countryRepository.deleteById(id);
    }
}
