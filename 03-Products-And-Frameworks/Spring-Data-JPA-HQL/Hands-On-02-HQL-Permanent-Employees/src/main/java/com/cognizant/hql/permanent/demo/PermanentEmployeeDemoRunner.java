package com.cognizant.hql.permanent.demo;

import com.cognizant.hql.permanent.model.Employee;
import com.cognizant.hql.permanent.service.PermanentEmployeeService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class PermanentEmployeeDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(PermanentEmployeeDemoRunner.class);

    private final PermanentEmployeeService permanentEmployeeService;

    public PermanentEmployeeDemoRunner(PermanentEmployeeService permanentEmployeeService) {
        this.permanentEmployeeService = permanentEmployeeService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 02: permanent employees & fetch join ===");

        System.out.println("\n-- Query 1: no fetch join (watch N+1 lazy loads when accessing department) --");
        List<Employee> withoutFetch = permanentEmployeeService.loadPermanentWithoutFetch();
        withoutFetch.forEach(e -> System.out.println(e + " -> dept: " + e.getDepartment().getName()));

        System.out.println("\n-- Query 2: LEFT JOIN FETCH department (single round-trip) --");
        List<Employee> withFetch = permanentEmployeeService.loadPermanentWithFetchJoin();
        withFetch.forEach(e -> System.out.println(e + " -> dept: " + e.getDepartment().getName()));

        log.info("=== Hands-On 02 complete ===");
    }
}
