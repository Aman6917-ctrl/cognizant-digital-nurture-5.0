package com.cognizant.ormcompare.springdata.repository;

import com.cognizant.ormcompare.springdata.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCountryCode(String countryCode);
}
