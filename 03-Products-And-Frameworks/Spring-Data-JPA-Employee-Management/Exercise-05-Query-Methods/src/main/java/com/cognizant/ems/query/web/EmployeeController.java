package com.cognizant.ems.query.web;

import com.cognizant.ems.query.dto.EmployeeRequest;
import com.cognizant.ems.query.entity.Employee;
import com.cognizant.ems.query.service.EmployeeService;
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
    public List<Employee> list() {
        return employeeService.findAll();
    }


    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String q) {
        return employeeService.searchByName(q);
    }

    @GetMapping("/search/by-domain")
    public List<Employee> searchByDomain(@RequestParam String domain) {
        return employeeService.searchByEmailDomain(domain);
    }

    @GetMapping("/by-department/{departmentId}")
    public List<Employee> byDepartment(@PathVariable Long departmentId) {
        return employeeService.findByDepartmentId(departmentId);
    }

    @GetMapping("/{id}")
    public Employee get(@PathVariable Long id) {
        return employeeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@Valid @RequestBody EmployeeRequest request) {
        return employeeService.create(request);
    }

    @PutMapping("/{id}")
    public Employee update(@PathVariable Long id, @Valid @RequestBody EmployeeRequest request) {
        return employeeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        employeeService.delete(id);
    }
}
