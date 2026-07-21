package com.cognizant.ems.auditing;

import com.cognizant.ems.auditing.entity.Department;
import com.cognizant.ems.auditing.entity.Employee;
import com.cognizant.ems.auditing.repository.DepartmentRepository;
import com.cognizant.ems.auditing.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class JpaAuditingIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void auditFieldsPopulated() {
        Department d = departmentRepository.save(Department.builder().name("AuditDept").build());
        Employee saved = employeeRepository.save(Employee.builder()
                .name("Eve")
                .email("eve@cognizant.com")
                .department(d)
                .build());
        assertThat(saved.getCreatedBy()).isEqualTo("training-user");
        assertThat(saved.getCreatedAt()).isNotNull();
    }
}
