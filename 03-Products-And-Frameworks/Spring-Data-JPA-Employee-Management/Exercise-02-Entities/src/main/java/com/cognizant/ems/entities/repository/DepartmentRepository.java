package com.cognizant.ems.entities.repository;

import com.cognizant.ems.entities.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
