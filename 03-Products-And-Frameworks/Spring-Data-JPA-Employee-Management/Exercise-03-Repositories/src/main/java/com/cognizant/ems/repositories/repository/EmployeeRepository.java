package com.cognizant.ems.repositories.repository;

import com.cognizant.ems.repositories.entity.Department;
import com.cognizant.ems.repositories.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByNameContainingIgnoreCase(String namePart);

    List<Employee> findByDepartment_Name(String departmentName);
}
