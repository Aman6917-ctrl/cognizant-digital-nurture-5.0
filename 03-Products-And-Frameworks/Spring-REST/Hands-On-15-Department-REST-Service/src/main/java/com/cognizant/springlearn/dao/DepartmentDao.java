package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Department;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    private static List<Department> DEPARTMENT_LIST = new ArrayList<>();

    public DepartmentDao() {
        LOGGER.info("START DepartmentDao constructor - loading employee.xml");
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("employee.xml")) {
            @SuppressWarnings("unchecked")
            List<Department> loaded = (List<Department>) context.getBean("departmentList");
            DEPARTMENT_LIST = new ArrayList<>(loaded);
            LOGGER.info("XML loading complete: {} departments", DEPARTMENT_LIST.size());
        }
        LOGGER.info("END DepartmentDao constructor");
    }

    public List<Department> getAllDepartments() {
        LOGGER.info("START getAllDepartments DAO");
        List<Department> departments = Collections.unmodifiableList(new ArrayList<>(DEPARTMENT_LIST));
        LOGGER.debug("Returning {} departments", departments.size());
        LOGGER.info("END getAllDepartments DAO");
        return departments;
    }
}
