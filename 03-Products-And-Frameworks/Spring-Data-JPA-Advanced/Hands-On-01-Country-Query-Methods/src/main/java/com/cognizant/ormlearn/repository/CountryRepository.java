package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {

    List<Country> findByNameContaining(String nameFragment);

    List<Country> findByNameContainingOrderByNameAsc(String nameFragment);

    List<Country> findByNameStartingWith(String prefix);
}
