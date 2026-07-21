package com.cognizant.ems.auditing.service;

import com.cognizant.ems.auditing.dto.EmployeeRequest;
import com.cognizant.ems.auditing.entity.Employee;
import com.cognizant.ems.auditing.exception.ResourceNotFoundException;
import com.cognizant.ems.auditing.repository.DepartmentRepository;
import com.cognizant.ems.auditing.repository.EmployeeRepository;
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

    public List<Employee> findAll() { return employeeRepository.findAll(); }

    public Employee create(EmployeeRequest request) {
        var dept = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));
        return employeeRepository.save(Employee.builder()
                .name(request.getName())
                .email(request.getEmail())
                .department(dept)
                .build());
    }
}
