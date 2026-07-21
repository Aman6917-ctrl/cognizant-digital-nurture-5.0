package com.cognizant.ems.auditing.web;

import com.cognizant.ems.auditing.dto.EmployeeRequest;
import com.cognizant.ems.auditing.entity.Employee;
import com.cognizant.ems.auditing.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping
    public List<Employee> list() { return employeeService.findAll(); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody EmployeeRequest request) {
        return employeeService.create(request);
    }
}
