package com.cognizant.ems.hibernate.service;

import com.cognizant.ems.hibernate.entity.Department;
import com.cognizant.ems.hibernate.entity.Employee;
import com.cognizant.ems.hibernate.repository.DepartmentRepository;
import com.cognizant.ems.hibernate.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class BatchInsertDemoService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    private final AtomicInteger lastBatchCount = new AtomicInteger(0);

    @Transactional
    public int insertManyEmployees(int count) {
        Department department = departmentRepository.findAll().stream().findFirst()
                .orElseGet(() -> departmentRepository.save(Department.builder().name("BatchDept").build()));
        List<Employee> batch = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            batch.add(Employee.builder()
                    .name("BatchUser" + i)
                    .email("batch" + i + "@cognizant.com")
                    .department(department)
                    .build());
        }
        employeeRepository.saveAll(batch);
        lastBatchCount.set(count);
        return count;
    }

    public int getLastBatchCount() {
        return lastBatchCount.get();
    }

    public long totalEmployees() {
        return employeeRepository.count();
    }
}
