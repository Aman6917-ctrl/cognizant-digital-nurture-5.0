package com.cognizant.springlearn.web;

import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@Valid @RequestBody Employee employee) {
        LOGGER.info("START PUT /employees controller id={}", employee.getId());
        Employee updated = employeeService.updateEmployee(employee);
        LOGGER.info("END PUT /employees controller");
        return updated;
    }
}
