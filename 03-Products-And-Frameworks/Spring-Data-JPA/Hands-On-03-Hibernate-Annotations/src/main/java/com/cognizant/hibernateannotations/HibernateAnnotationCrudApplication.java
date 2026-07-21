package com.cognizant.hibernateannotations;

import com.cognizant.hibernateannotations.config.HibernateConfigHelper;
import com.cognizant.hibernateannotations.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateAnnotationCrudApplication {

    private static final Logger log = LoggerFactory.getLogger(HibernateAnnotationCrudApplication.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfigHelper.buildSessionFactory();
        try {
            runCrud(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    private static void runCrud(SessionFactory sessionFactory) {
        log.info("=== CRUD with annotation-mapped Country ===");

        Long savedId;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Country country = new Country("FR", "France");
            session.persist(country);
            tx.commit();
            savedId = country.getCountryId();
            log.info("SAVE: {}", country);
        }

        try (Session session = sessionFactory.openSession()) {
            Country loaded = session.get(Country.class, savedId);
            log.info("GET: {}", loaded);
        }

        try (Session session = sessionFactory.openSession()) {
            List<Country> countries = session.createQuery("from Country order by countryId", Country.class).list();
            log.info("LIST ({}):", countries.size());
            countries.forEach(c -> log.info("  {}", c));
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Country country = session.get(Country.class, savedId);
            country.setCountryName("French Republic");
            tx.commit();
            log.info("UPDATE: {}", country);
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Country country = session.get(Country.class, savedId);
            session.remove(country);
            tx.commit();
            log.info("DELETE: removed id {}", savedId);
        }

        try (Session session = sessionFactory.openSession()) {
            Country gone = session.get(Country.class, savedId);
            log.info("GET after delete: {}", gone);
        }
    }
}
