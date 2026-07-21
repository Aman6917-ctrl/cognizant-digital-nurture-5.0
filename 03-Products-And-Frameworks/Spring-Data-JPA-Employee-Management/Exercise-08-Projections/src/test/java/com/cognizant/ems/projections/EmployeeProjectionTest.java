package com.cognizant.ems.projections;

import com.cognizant.ems.projections.entity.Department;
import com.cognizant.ems.projections.entity.Employee;
import com.cognizant.ems.projections.repository.DepartmentRepository;
import com.cognizant.ems.projections.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeProjectionTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void seed() {
        Department d = departmentRepository.save(Department.builder().name("Dev").build());
        employeeRepository.save(Employee.builder().name("Frank").email("frank@cognizant.com").department(d).build());
    }

    @Test
    void interfaceProjection() {
        assertThat(employeeRepository.findAllProjectedBy()).hasSize(1);
        assertThat(employeeRepository.findAllProjectedBy().get(0).getEmail()).contains("frank");
    }

    @Test
    void constructorProjection() {
        assertThat(employeeRepository.findAllConstructorDtos()).hasSize(1);
    }
}
