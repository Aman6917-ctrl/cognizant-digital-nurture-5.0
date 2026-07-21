package com.cognizant.relationships.demo;

import com.cognizant.relationships.model.Department;
import com.cognizant.relationships.service.RelationshipFetchService;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@Order(3)
public class HandsOn05OneToManyLazyDemo implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn05OneToManyLazyDemo.class);

    private final RelationshipFetchService relationshipFetchService;

    public HandsOn05OneToManyLazyDemo(RelationshipFetchService relationshipFetchService) {
        this.relationshipFetchService = relationshipFetchService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 5a: @OneToMany LAZY outside transaction ===");

        Department department = relationshipFetchService.loadDepartmentOutsideTransaction(1L);
        log.info("Department loaded (detached): {}", department);

        try {
            relationshipFetchService.tryAccessEmployeesOutsideTransaction(department)
                    .forEach(e -> System.out.println(e));
        } catch (LazyInitializationException ex) {
            log.error("Expected LazyInitializationException when calling getEmployees() outside a session");
            System.out.println("Caught LazyInitializationException: " + ex.getClass().getSimpleName());
        }

        log.info("=== Hands-On 5a complete ===");
    }
}
