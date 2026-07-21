package com.cognizant.hibernatexml;

import com.cognizant.hibernatexml.config.HibernateConfigHelper;
import com.cognizant.hibernatexml.model.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class HibernateXmlCrudApplication {

    private static final Logger log = LoggerFactory.getLogger(HibernateXmlCrudApplication.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateConfigHelper.buildSessionFactory();
        try {
            demonstrateCrud(sessionFactory);
            demonstrateRollback(sessionFactory);
        } finally {
            sessionFactory.close();
        }
    }

    private static void demonstrateCrud(SessionFactory sessionFactory) {
        log.info("=== CRUD with XML-mapped Country ===");

        Long savedId;
        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Country india = new Country("IN", "India");
            session.persist(india);
            savedId = india.getCountryId();
            tx.commit();
            log.info("SAVE: persisted {} with id {}", india, savedId);
        }

        try (Session session = sessionFactory.openSession()) {
            Country loaded = session.get(Country.class, savedId);
            log.info("GET by id {}: {}", savedId, loaded);
        }

        try (Session session = sessionFactory.openSession()) {
            List<Country> all = session.createQuery("from Country order by countryId", Country.class).list();
            log.info("LIST all ({} rows):", all.size());
            all.forEach(c -> log.info("  {}", c));
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Country toUpdate = session.get(Country.class, savedId);
            toUpdate.setCountryName("Republic of India");
            session.merge(toUpdate);
            tx.commit();
            log.info("UPDATE: {}", toUpdate);
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            Country toDelete = session.get(Country.class, savedId);
            session.remove(toDelete);
            tx.commit();
            log.info("DELETE: removed country id {}", savedId);
        }

        try (Session session = sessionFactory.openSession()) {
            Country afterDelete = session.get(Country.class, savedId);
            log.info("GET after delete: {}", afterDelete);
        }
    }

    private static void demonstrateRollback(SessionFactory sessionFactory) {
        log.info("=== Transaction rollback demo ===");
        long countBefore;
        try (Session session = sessionFactory.openSession()) {
            countBefore = session.createQuery("select count(c) from Country c", Long.class).uniqueResult();
        }

        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(new Country("ZZ", "Rollback Land"));
            session.persist(new Country("ZZ", "Duplicate Code Forces Failure"));
            tx.commit();
        } catch (RuntimeException ex) {
            if (tx != null && tx.isActive()) {
                tx.rollback();
                log.warn("Transaction rolled back due to: {}", ex.getMessage());
            }
        }

        try (Session session = sessionFactory.openSession()) {
            long countAfter = session.createQuery("select count(c) from Country c", Long.class).uniqueResult();
            log.info("Row count before failed txn: {}, after rollback: {}", countBefore, countAfter);
            if (countBefore == countAfter) {
                log.info("Rollback verified — no rows committed from failed transaction.");
            }
        }
    }
}
