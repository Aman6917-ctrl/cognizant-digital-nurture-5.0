package com.cognizant.relationships.demo;

import com.cognizant.relationships.model.Department;
import com.cognizant.relationships.model.Employee;
import com.cognizant.relationships.model.Skill;
import com.cognizant.relationships.service.DepartmentService;
import com.cognizant.relationships.service.EmployeeService;
import com.cognizant.relationships.service.SkillService;
import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
@Order(1)
public class HandsOn03EntityMappingDemo implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(HandsOn03EntityMappingDemo.class);

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;
    private final SkillService skillService;

    public HandsOn03EntityMappingDemo(
            DepartmentService departmentService,
            EmployeeService employeeService,
            SkillService skillService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
        this.skillService = skillService;
    }

    @Override
    public void run(String... args) {
        log.info("=== Hands-On 3: Entity mapping (save / get) ===");

        Department dept = departmentService.get(1L);
        log.info("Loaded department: {}", dept);

        Employee employee = employeeService.get(1L);
        log.info("Loaded employee: {}, department id={}", employee, employee.getDepartment().getId());

        Skill skill = skillService.get(1L);
        log.info("Loaded skill: {}", skill);

        Department newDept = departmentService.save(new Department("Research"));
        log.info("Saved department: {}", newDept);

        Employee newEmployee = new Employee("Frank Lee", new BigDecimal("82000.00"));
        newEmployee.setDepartment(newDept);
        newEmployee = employeeService.save(newEmployee);
        log.info("Saved employee: {}", newEmployee);

        Skill newSkill = skillService.save(new Skill("Kubernetes"));
        log.info("Saved skill: {}", newSkill);

        log.info("=== Hands-On 3 complete ===");
    }
}
