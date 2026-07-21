package com.cognizant.ems.projections.repository;

import com.cognizant.ems.projections.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
