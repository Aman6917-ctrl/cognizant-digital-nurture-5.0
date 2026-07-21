package com.cognizant.ems.hibernate.repository;

import com.cognizant.ems.hibernate.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
