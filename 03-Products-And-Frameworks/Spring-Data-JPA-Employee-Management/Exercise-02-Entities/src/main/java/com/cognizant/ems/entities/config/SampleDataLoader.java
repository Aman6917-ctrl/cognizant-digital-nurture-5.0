package com.cognizant.ems.entities.config;

import com.cognizant.ems.entities.entity.Department;
import com.cognizant.ems.entities.entity.Employee;
import com.cognizant.ems.entities.repository.DepartmentRepository;
import com.cognizant.ems.entities.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SampleDataLoader {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Bean
    CommandLineRunner loadSampleData() {
        return args -> {
            if (departmentRepository.count() > 0) {
                return;
            }
            Department engineering = Department.builder().name("Engineering").build();
            departmentRepository.save(engineering);
            Employee alice = Employee.builder()
                    .name("Alice Smith")
                    .email("alice@cognizant.com")
                    .department(engineering)
                    .build();
            employeeRepository.save(alice);
        };
    }
}
