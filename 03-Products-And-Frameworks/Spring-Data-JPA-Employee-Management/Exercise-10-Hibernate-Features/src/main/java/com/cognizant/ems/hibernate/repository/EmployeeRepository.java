package com.cognizant.ems.hibernate.repository;

import com.cognizant.ems.hibernate.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
