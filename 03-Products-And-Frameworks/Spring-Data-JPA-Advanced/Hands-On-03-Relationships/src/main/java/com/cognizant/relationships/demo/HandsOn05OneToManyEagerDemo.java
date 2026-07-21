package com.cognizant.relationships.demo;

import com.cognizant.relationships.model.Department;
import com.cognizant.relationships.service.RelationshipFetchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@Order(4)
public class HandsOn05OneToManyEagerDemo implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn05OneToManyEagerDemo.class);

    private final RelationshipFetchService relationshipFetchService;

    public HandsOn05OneToManyEagerDemo(RelationshipFetchService relationshipFetchService) {
        this.relationshipFetchService = relationshipFetchService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 5b: @OneToMany with JOIN FETCH (eager load in query) ===");

        Department department = relationshipFetchService.loadDepartmentWithEmployees(1L);
        log.info("Department with employees initialized in transaction: {}", department);

        System.out.println("-- Employees for " + department.getName() + " --");
        department.getEmployees().forEach(System.out::println);

        log.info("=== Hands-On 5b complete ===");
    }
}
