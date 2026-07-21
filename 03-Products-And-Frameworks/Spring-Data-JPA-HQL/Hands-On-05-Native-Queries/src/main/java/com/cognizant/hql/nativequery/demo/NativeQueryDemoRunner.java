package com.cognizant.hql.nativequery.demo;

import com.cognizant.hql.nativequery.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Profile("!test")
public class NativeQueryDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(NativeQueryDemoRunner.class);

    private final EmployeeRepository employeeRepository;

    public NativeQueryDemoRunner(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        log.info("=== Hands-On 05: native SQL queries ===");

        System.out.println("\n-- Native SELECT * FROM employee --");
        employeeRepository.findAllNative().forEach(System.out::println);

        System.out.println("\n-- Native COUNT permanent --");
        System.out.println("Permanent count: " + employeeRepository.countPermanentNative());

        log.info("=== Hands-On 05 complete ===");
    }
}
