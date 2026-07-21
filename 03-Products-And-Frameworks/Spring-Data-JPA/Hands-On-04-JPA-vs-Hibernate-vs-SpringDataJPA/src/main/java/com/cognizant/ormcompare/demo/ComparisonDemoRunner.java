package com.cognizant.ormcompare.demo;

import com.cognizant.ormcompare.hibernate.HibernateCountryDao;
import com.cognizant.ormcompare.springdata.model.Country;
import com.cognizant.ormcompare.springdata.service.SpringDataCountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class ComparisonDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ComparisonDemoRunner.class);

    private final HibernateCountryDao hibernateCountryDao;
    private final SpringDataCountryService springDataCountryService;

    public ComparisonDemoRunner(HibernateCountryDao hibernateCountryDao,
                                SpringDataCountryService springDataCountryService) {
        this.hibernateCountryDao = hibernateCountryDao;
        this.springDataCountryService = springDataCountryService;
    }

    @Override
    public void run(String... args) {
        log.info("========== Hibernate Session API path ==========");
        hibernateCountryDao.findAll().forEach(c -> log.info("  {}", c));
        hibernateCountryDao.findByCode("IN").ifPresent(c -> log.info("  Found IN: {}", c));

        Country hibernateSaved = hibernateCountryDao.save(new Country("DE", "Germany"));
        hibernateCountryDao.update(hibernateSaved.getCountryId(), "Federal Republic of Germany");
        hibernateCountryDao.findById(hibernateSaved.getCountryId()).ifPresent(c -> log.info("  After update: {}", c));
        hibernateCountryDao.deleteById(hibernateSaved.getCountryId());

        log.info("========== Spring Data JPA path ==========");
        springDataCountryService.findAll().forEach(c -> log.info("  {}", c));
        springDataCountryService.findByCode("US").ifPresent(c -> log.info("  Found US: {}", c));

        Country sdSaved = springDataCountryService.save(new Country("JP", "Japan"));
        springDataCountryService.update(sdSaved.getCountryId(), "Japan (Nihon)");
        springDataCountryService.findByCode("JP").ifPresent(c -> log.info("  After update: {}", c));
        springDataCountryService.deleteById(sdSaved.getCountryId());

        log.info("========== Comparison demo complete ==========");
    }
}
