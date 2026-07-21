package com.cognizant.ems.pagination;

import com.cognizant.ems.pagination.entity.Department;
import com.cognizant.ems.pagination.entity.Employee;
import com.cognizant.ems.pagination.repository.DepartmentRepository;
import com.cognizant.ems.pagination.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class EmployeePaginationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void seed() {
        Department d = departmentRepository.save(Department.builder().name("Ops").build());
        for (int i = 0; i < 15; i++) {
            employeeRepository.save(Employee.builder()
                    .name("Employee " + i)
                    .email("e" + i + "@cognizant.com")
                    .department(d)
                    .build());
        }
    }

    @Test
    void pageAndSort() {
        var page = employeeRepository.findAll(PageRequest.of(0, 10, Sort.by("name").ascending()));
        assertThat(page.getTotalElements()).isEqualTo(15);
        assertThat(page.getContent()).hasSize(10);
    }
}
