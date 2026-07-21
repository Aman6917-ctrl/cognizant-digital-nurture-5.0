package com.cognizant.hibernateannotations.config;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateConfigHelper {

    private HibernateConfigHelper() {
    }

    public static SessionFactory buildSessionFactory() {
        String host = env("MYSQL_HOST", "localhost");
        String port = env("MYSQL_PORT", "3306");
        String database = env("MYSQL_DATABASE", "country_db");
        String user = env("MYSQL_USER", "root");
        String password = env("MYSQL_PASSWORD", "root");

        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + database
                + "?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        Configuration configuration = new Configuration().configure();
        configuration.setProperty("hibernate.connection.url", jdbcUrl);
        configuration.setProperty("hibernate.connection.username", user);
        configuration.setProperty("hibernate.connection.password", password);
        return configuration.buildSessionFactory();
    }

    private static String env(String key, String defaultValue) {
        String value = System.getenv(key);
        if (value == null || value.isBlank()) {
            return defaultValue;
        }
        return value.trim();
    }
}
