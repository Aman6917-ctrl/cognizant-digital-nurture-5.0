package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private static List<Employee> EMPLOYEE_LIST = new ArrayList<>();

    public EmployeeDao() {
        LOGGER.info("START EmployeeDao constructor - loading employee.xml");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("employee.xml")) {
            @SuppressWarnings("unchecked")
            List<Employee> loaded = (List<Employee>) context.getBean("employeeList");
            EMPLOYEE_LIST = new ArrayList<>(loaded);
            LOGGER.info("Loaded {} employees", EMPLOYEE_LIST.size());
        }
        LOGGER.info("END EmployeeDao constructor");
    }

    public Employee updateEmployee(Employee employee) {
        LOGGER.info("START updateEmployee DAO id={}", employee.getId());
        for (int index = 0; index < EMPLOYEE_LIST.size(); index++) {
            if (EMPLOYEE_LIST.get(index).getId().equals(employee.getId())) {
                EMPLOYEE_LIST.set(index, employee);
                LOGGER.info("END updateEmployee DAO - updated");
                return employee;
            }
        }
        LOGGER.warn("Employee id={} not found for update", employee.getId());
        throw new EmployeeNotFoundException(employee.getId());
    }
}
