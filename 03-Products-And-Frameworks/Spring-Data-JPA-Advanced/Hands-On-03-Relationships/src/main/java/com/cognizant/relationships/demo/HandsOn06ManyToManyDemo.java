package com.cognizant.relationships.demo;

import com.cognizant.relationships.model.Employee;
import com.cognizant.relationships.model.Skill;
import com.cognizant.relationships.service.EmployeeService;
import com.cognizant.relationships.service.RelationshipFetchService;
import com.cognizant.relationships.service.SkillService;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@Order(5)
public class HandsOn06ManyToManyDemo implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn06ManyToManyDemo.class);

    private final RelationshipFetchService relationshipFetchService;
    private final EmployeeService employeeService;
    private final SkillService skillService;

    public HandsOn06ManyToManyDemo(
            RelationshipFetchService relationshipFetchService,
            EmployeeService employeeService,
            SkillService skillService) {
        this.relationshipFetchService = relationshipFetchService;
        this.employeeService = employeeService;
        this.skillService = skillService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 6: @ManyToMany LAZY vs JOIN FETCH ===");

        Employee detached = relationshipFetchService.loadEmployeeOutsideTransaction(1L);
        log.info("Employee loaded (detached): {}", detached);

        try {
            relationshipFetchService.tryAccessSkillsOutsideTransaction(detached)
                    .forEach(System.out::println);
        } catch (LazyInitializationException ex) {
            log.error("Expected LazyInitializationException when accessing skills outside a session");
            System.out.println("Caught LazyInitializationException: " + ex.getClass().getSimpleName());
        }

        Employee withSkills = relationshipFetchService.loadEmployeeWithSkills(1L);
        System.out.println("-- Skills for " + withSkills.getName() + " (JOIN FETCH) --");
        withSkills.getSkills().forEach(System.out::println);

        Employee managed = employeeService.get(1L);
        Skill communication = skillService.get(4L);
        if (!managed.getSkills().contains(communication)) {
            managed.addSkill(communication);
        }
        Employee persisted = employeeService.save(managed);
        log.info("Persisted employee {} with {} skills", persisted.getName(), persisted.getSkills().size());

        log.info("=== Hands-On 6 complete ===");
    }
}
