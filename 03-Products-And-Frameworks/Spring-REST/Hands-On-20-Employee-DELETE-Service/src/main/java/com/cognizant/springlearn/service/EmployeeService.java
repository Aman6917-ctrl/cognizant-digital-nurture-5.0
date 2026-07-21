package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    private final EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        LOGGER.info("START getAllEmployees service");
        List<Employee> employees = employeeDao.getAllEmployees();
        LOGGER.info("END getAllEmployees service");
        return employees;
    }

    @Transactional
    public void deleteEmployee(Long id) {
        LOGGER.info("START deleteEmployee service id={}", id);
        employeeDao.deleteEmployee(id);
        LOGGER.info("END deleteEmployee service");
    }
}
