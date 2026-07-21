package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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

    public List<Employee> getAllEmployees() {
        LOGGER.info("START getAllEmployees DAO");
        List<Employee> employees = Collections.unmodifiableList(new ArrayList<>(EMPLOYEE_LIST));
        LOGGER.info("END getAllEmployees DAO count={}", employees.size());
        return employees;
    }

    public void deleteEmployee(Long id) {
        LOGGER.info("START deleteEmployee DAO id={}", id);
        Iterator<Employee> iterator = EMPLOYEE_LIST.iterator();
        while (iterator.hasNext()) {
            Employee employee = iterator.next();
            if (employee.getId().equals(id)) {
                iterator.remove();
                LOGGER.info("END deleteEmployee DAO - removed id={}", id);
                return;
            }
        }
        LOGGER.warn("Employee id={} not found for delete", id);
        throw new EmployeeNotFoundException(id);
    }
}
