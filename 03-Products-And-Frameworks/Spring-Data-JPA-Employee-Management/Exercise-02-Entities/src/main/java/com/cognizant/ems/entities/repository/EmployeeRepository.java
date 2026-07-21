package com.cognizant.ems.entities.repository;

import com.cognizant.ems.entities.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
