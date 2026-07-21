package com.cognizant.hql.permanent.service;

import com.cognizant.hql.permanent.model.Employee;
import com.cognizant.hql.permanent.repository.EmployeeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermanentEmployeeService {

    private final EmployeeRepository employeeRepository;

    public PermanentEmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<Employee> loadPermanentWithoutFetch() {
        return employeeRepository.findPermanentEmployees();
    }

    @Transactional(readOnly = true)
    public List<Employee> loadPermanentWithFetchJoin() {
        return employeeRepository.findPermanentEmployeesWithDepartmentFetch();
    }
}
