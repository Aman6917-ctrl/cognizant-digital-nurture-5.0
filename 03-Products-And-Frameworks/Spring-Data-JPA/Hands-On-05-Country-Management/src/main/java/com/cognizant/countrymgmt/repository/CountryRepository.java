package com.cognizant.countrymgmt.repository;

import com.cognizant.countrymgmt.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    Optional<Country> findByCountryCode(String countryCode);

    List<Country> findByCountryNameContainingIgnoreCase(String namePart);
}
