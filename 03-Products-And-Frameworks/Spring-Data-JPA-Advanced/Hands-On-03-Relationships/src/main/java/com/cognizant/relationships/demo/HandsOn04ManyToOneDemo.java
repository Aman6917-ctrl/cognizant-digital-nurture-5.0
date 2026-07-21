package com.cognizant.relationships.demo;

import com.cognizant.relationships.model.Department;
import com.cognizant.relationships.model.Employee;
import com.cognizant.relationships.service.DepartmentService;
import com.cognizant.relationships.service.EmployeeService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@Order(2)
public class HandsOn04ManyToOneDemo implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn04ManyToOneDemo.class);

    private final EmployeeService employeeService;
    private final DepartmentService departmentService;

    public HandsOn04ManyToOneDemo(EmployeeService employeeService, DepartmentService departmentService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 4: @ManyToOne (watch SQL in logs) ===");
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();
        log.info("=== Hands-On 4 complete ===");
    }

    void testGetEmployee() {
        log.info("-- testGetEmployee --");
        Employee employee = employeeService.get(1L);
        System.out.println(employee);
        System.out.println("Department: " + employee.getDepartment().getName());
    }

    void testAddEmployee() {
        log.info("-- testAddEmployee --");
        Department engineering = departmentService.get(1L);
        Employee employee = new Employee("Grace Park", new BigDecimal("84000.00"));
        employee.setDepartment(engineering);
        Employee saved = employeeService.save(employee);
        System.out.println("Inserted: " + saved);
    }

    void testUpdateEmployee() {
        log.info("-- testUpdateEmployee --");
        Employee employee = employeeService.get(2L);
        employee.setSalary(new BigDecimal("92000.00"));
        Employee updated = employeeService.save(employee);
        System.out.println("Updated salary: " + updated.getSalary());
    }
}
