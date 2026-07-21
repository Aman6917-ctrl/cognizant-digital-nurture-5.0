package com.cognizant.hql.aggregate.demo;

import com.cognizant.hql.aggregate.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class AggregateDemoRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(AggregateDemoRunner.class);

    private final EmployeeRepository employeeRepository;

    public AggregateDemoRunner(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 04: JPQL aggregate functions ===");

        System.out.println("AVG salary (Engineering): " + employeeRepository.averageSalaryByDepartmentName("Engineering"));
        System.out.println("Employee counts by department:");
        employeeRepository.countEmployeesByDepartment().forEach(row ->
                System.out.println("  " + row[0] + " -> " + row[1]));
        System.out.println("MAX salary (dept 1): " + employeeRepository.maxSalary(1L));
        System.out.println("MIN salary (dept 1): " + employeeRepository.minSalary(1L));
        System.out.println("SUM salary (permanent): " + employeeRepository.sumSalaryForPermanentEmployees());

        log.info("=== Hands-On 04 complete ===");
    }
}
