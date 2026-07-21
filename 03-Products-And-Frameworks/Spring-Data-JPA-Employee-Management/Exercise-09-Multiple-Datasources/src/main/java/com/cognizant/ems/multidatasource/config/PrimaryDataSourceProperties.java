package com.cognizant.ems.multidatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.primary")
public record PrimaryDataSourceProperties(String jdbcUrl, String driverClassName, String username, String password) {
}
