package com.cognizant.ormcompare.hibernate;

import com.cognizant.ormcompare.springdata.model.Country;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateCountryDao {

    private static final Logger log = LoggerFactory.getLogger(HibernateCountryDao.class);

    @PersistenceContext
    private EntityManager entityManager;

    private Session session() {
        return entityManager.unwrap(Session.class);
    }

    @Transactional
    public Country save(Country country) {
        log.info("[Hibernate Session] persist {}", country);
        session().persist(country);
        return country;
    }

    @Transactional(readOnly = true)
    public Optional<Country> findById(Long id) {
        log.info("[Hibernate Session] get Country id={}", id);
        return Optional.ofNullable(session().get(Country.class, id));
    }

    @Transactional(readOnly = true)
    public List<Country> findAll() {
        log.info("[Hibernate Session] HQL list all countries");
        return session().createQuery("from Country order by countryId", Country.class).list();
    }

    @Transactional(readOnly = true)
    public Optional<Country> findByCode(String code) {
        log.info("[Hibernate Session] HQL find by code {}", code);
        return session()
                .createQuery("from Country c where c.countryCode = :code", Country.class)
                .setParameter("code", code)
                .uniqueResultOptional();
    }

    @Transactional
    public Country update(Long id, String newName) {
        Country country = session().get(Country.class, id);
        if (country == null) {
            throw new IllegalArgumentException("Country not found: " + id);
        }
        country.setCountryName(newName);
        log.info("[Hibernate Session] update id {} name to {}", id, newName);
        return country;
    }

    @Transactional
    public void deleteById(Long id) {
        Country country = session().get(Country.class, id);
        if (country != null) {
            log.info("[Hibernate Session] remove id {}", id);
            session().remove(country);
        }
    }
}
