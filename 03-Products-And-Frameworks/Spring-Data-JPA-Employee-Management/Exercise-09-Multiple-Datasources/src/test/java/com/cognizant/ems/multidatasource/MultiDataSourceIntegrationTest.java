package com.cognizant.ems.multidatasource;

import com.cognizant.ems.multidatasource.primary.entity.Department;
import com.cognizant.ems.multidatasource.primary.entity.Employee;
import com.cognizant.ems.multidatasource.primary.repository.EmployeeRepository;
import com.cognizant.ems.multidatasource.secondary.entity.AuditEvent;
import com.cognizant.ems.multidatasource.secondary.repository.AuditEventRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MultiDataSourceIntegrationTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AuditEventRepository auditEventRepository;

    @Test
    void bothDatasourcesWork() {
        Department d = Department.builder().name("PrimaryDept").build();
        Employee e = Employee.builder().name("Grace").email("grace@c.com").department(d).build();
        employeeRepository.save(e);
        auditEventRepository.save(AuditEvent.builder().action("EMPLOYEE_LIST").occurredAt(Instant.now()).build());
        assertThat(employeeRepository.findAll()).isNotEmpty();
        assertThat(auditEventRepository.findAll()).isNotEmpty();
    }
}
