package com.cognizant.ems.repositories;

import com.cognizant.ems.repositories.entity.Department;
import com.cognizant.ems.repositories.entity.Employee;
import com.cognizant.ems.repositories.repository.DepartmentRepository;
import com.cognizant.ems.repositories.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department engineering;

    @BeforeEach
    void setUp() {
        engineering = departmentRepository.save(Department.builder().name("Engineering").build());
        employeeRepository.save(Employee.builder().name("Alice Smith").email("alice@cognizant.com").department(engineering).build());
        employeeRepository.save(Employee.builder().name("Bob Jones").email("bob@cognizant.com").department(engineering).build());
    }

    @Test
    void findByEmail() {
        assertThat(employeeRepository.findByEmail("alice@cognizant.com")).isPresent();
    }

    @Test
    void findByNameContaining() {
        List<Employee> found = employeeRepository.findByNameContainingIgnoreCase("alice");
        assertThat(found).hasSize(1);
    }

    @Test
    void findByDepartmentName() {
        List<Employee> found = employeeRepository.findByDepartment_Name("Engineering");
        assertThat(found).hasSize(2);
    }
}
