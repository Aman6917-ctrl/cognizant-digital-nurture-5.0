package com.cognizant.springlearn.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    PasswordEncoder passwordEncoder() {
        LOGGER.info("Registering BCryptPasswordEncoder bean");
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        LOGGER.info("START building in-memory users admin and user");
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("pwd"))
                .roles("ADMIN")
                .build();
        UserDetails standardUser = User.builder()
                .username("user")
                .password(passwordEncoder.encode("pwd"))
                .roles("USER")
                .build();
        LOGGER.info("END building in-memory users");
        return new InMemoryUserDetailsManager(admin, standardUser);
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        LOGGER.info("START SecurityFilterChain with role-based rules");
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/countries").hasRole("USER")
                        .requestMatchers("/employees").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults());
        LOGGER.info("END SecurityFilterChain configuration");
        return http.build();
    }
}
