package com.cognizant.ems.multidatasource.primary.repository;

import com.cognizant.ems.multidatasource.primary.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
