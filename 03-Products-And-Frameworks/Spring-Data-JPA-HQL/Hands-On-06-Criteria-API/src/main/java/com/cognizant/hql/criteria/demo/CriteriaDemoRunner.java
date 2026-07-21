package com.cognizant.hql.criteria.demo;

import com.cognizant.hql.criteria.model.Employee;
import com.cognizant.hql.criteria.search.EmployeeSearchCriteria;
import com.cognizant.hql.criteria.service.EmployeeSearchService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class CriteriaDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(CriteriaDemoRunner.class);

    private final EmployeeSearchService employeeSearchService;

    public CriteriaDemoRunner(EmployeeSearchService employeeSearchService) {
        this.employeeSearchService = employeeSearchService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 06: Criteria API dynamic search ===");

        runSearch("Permanent staff in Engineering",
                new EmployeeSearchCriteria(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of("Engineering"),
                        Optional.of(true),
                        Optional.empty(),
                        Optional.empty()));

        runSearch("Salary band 85000-95000",
                new EmployeeSearchCriteria(
                        Optional.of(new BigDecimal("85000")),
                        Optional.of(new BigDecimal("95000")),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty()));

        runSearch("Amazon-like name search 'a'",
                EmployeeSearchCriteria.amazonLike("a"));

        runSearch("Has Spring Boot skill",
                new EmployeeSearchCriteria(
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.empty(),
                        Optional.of("Spring Boot")));

        log.info("=== Hands-On 06 complete ===");
    }

    private void runSearch(String label, EmployeeSearchCriteria criteria) {
        System.out.println("\n-- " + label + " --");
        List<Employee> results = employeeSearchService.search(criteria);
        results.forEach(System.out::println);
        System.out.println("Matches: " + results.size());
    }
}
