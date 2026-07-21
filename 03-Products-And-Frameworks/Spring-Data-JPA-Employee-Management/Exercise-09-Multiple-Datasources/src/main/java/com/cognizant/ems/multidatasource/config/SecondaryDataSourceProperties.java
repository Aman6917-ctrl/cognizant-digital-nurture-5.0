package com.cognizant.ems.multidatasource.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "spring.datasource.secondary")
public record SecondaryDataSourceProperties(String jdbcUrl, String driverClassName, String username, String password) {
}
