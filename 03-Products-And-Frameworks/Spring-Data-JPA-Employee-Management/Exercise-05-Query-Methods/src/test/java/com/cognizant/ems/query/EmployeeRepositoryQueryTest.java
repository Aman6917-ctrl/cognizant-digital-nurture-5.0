package com.cognizant.ems.query;

import com.cognizant.ems.query.entity.Department;
import com.cognizant.ems.query.entity.Employee;
import com.cognizant.ems.query.repository.DepartmentRepository;
import com.cognizant.ems.query.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryQueryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        Department d = departmentRepository.save(Department.builder().name("HR").build());
        employeeRepository.save(Employee.builder().name("Carol").email("carol@cognizant.com").department(d).build());
    }

    @Test
    void jpqlDomainSearch() {
        assertThat(employeeRepository.findByEmailDomain("cognizant.com")).hasSize(1);
    }

    @Test
    void namedQueryByDepartment() {
        Department d = departmentRepository.findAll().get(0);
        assertThat(employeeRepository.findByDepartmentId(d.getId())).hasSize(1);
    }
}
