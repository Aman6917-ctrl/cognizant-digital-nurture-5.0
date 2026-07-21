package com.cognizant.hql.intro.service;

import com.cognizant.hql.intro.model.Employee;
import com.cognizant.hql.intro.repository.EmployeeRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class IntroQueryService {

    private final EmployeeRepository employeeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public IntroQueryService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> highEarners(BigDecimal minSalary) {
        return employeeRepository.findHighEarners(minSalary);
    }

    @Transactional(readOnly = true)
    public List<Employee> byDepartment(String deptName) {
        return employeeRepository.findByDepartmentName(deptName);
    }

    @Transactional
    public int applyRaise(String deptName, BigDecimal factor) {
        return employeeRepository.applyRaiseInDepartment(deptName, factor);
    }

    @Transactional
    public int removeTemporaryStaff() {
        return employeeRepository.deleteTemporaryEmployees();
    }

    @Transactional(readOnly = true)
    public List<Employee> hqlPermanentEmployees() {
        return entityManager
                .createQuery("FROM Employee e WHERE e.permanent = true", Employee.class)
                .getResultList();
    }
}
