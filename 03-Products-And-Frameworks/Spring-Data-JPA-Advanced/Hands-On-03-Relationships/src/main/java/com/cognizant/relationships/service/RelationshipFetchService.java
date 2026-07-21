package com.cognizant.relationships.service;

import com.cognizant.relationships.model.Department;
import com.cognizant.relationships.model.Employee;
import com.cognizant.relationships.model.Skill;
import com.cognizant.relationships.repository.DepartmentRepository;
import com.cognizant.relationships.repository.EmployeeRepository;
import java.util.List;
import java.util.Set;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Loads entities without initializing lazy associations, for fetch-strategy demos.
 * Each {@code load*} method ends its transaction before returning a detached entity.
 */
@Service
public class RelationshipFetchService {

    private static final Logger log = LoggerFactory.getLogger(RelationshipFetchService.class);

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    public RelationshipFetchService(
            DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Department loadDepartmentOutsideTransaction(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Department loadDepartmentWithEmployees(Long id) {
        return departmentRepository.findByIdWithEmployees(id)
                .orElseThrow(() -> new IllegalArgumentException("Department not found: " + id));
    }

    public List<Employee> tryAccessEmployeesOutsideTransaction(Department department) {
        try {
            return department.getEmployees();
        } catch (LazyInitializationException ex) {
            log.warn("LazyInitializationException accessing department.employees: {}", ex.getMessage());
            throw ex;
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Employee loadEmployeeOutsideTransaction(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = true)
    public Employee loadEmployeeWithSkills(Long id) {
        return employeeRepository.findByIdWithSkills(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
    }

    public Set<Skill> tryAccessSkillsOutsideTransaction(Employee employee) {
        try {
            return employee.getSkills();
        } catch (LazyInitializationException ex) {
            log.warn("LazyInitializationException accessing employee.skills: {}", ex.getMessage());
            throw ex;
        }
    }
}
