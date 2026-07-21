package com.cognizant.ems.query.service;

import com.cognizant.ems.query.dto.EmployeeRequest;
import com.cognizant.ems.query.entity.Employee;
import com.cognizant.ems.query.exception.ResourceNotFoundException;
import com.cognizant.ems.query.repository.DepartmentRepository;
import com.cognizant.ems.query.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found: " + id));
    }

    public Employee create(EmployeeRequest request) {
        var department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + request.getDepartmentId()));
        Employee employee = Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(department)
                .build();
        return employeeRepository.save(employee);
    }

    public Employee update(Long id, EmployeeRequest request) {
        Employee employee = findById(id);
        var department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + request.getDepartmentId()));
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found: " + id);
        }
        employeeRepository.deleteById(id);
    }

    public List<Employee> searchByName(String q) {
        return employeeRepository.findByNameContainingIgnoreCase(q);
    }

    public List<Employee> searchByEmailDomain(String domain) {
        return employeeRepository.findByEmailDomain(domain);
    }

    public List<Employee> findByDepartmentId(Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }
}
