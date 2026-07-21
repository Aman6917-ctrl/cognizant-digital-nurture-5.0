package com.cognizant.ems.query.repository;

import com.cognizant.ems.query.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmail(String email);

    List<Employee> findByNameContainingIgnoreCase(String namePart);

    List<Employee> findByDepartment_Name(String departmentName);

    @Query(name = "Employee.findByDepartmentId")
    List<Employee> findByDepartmentId(@Param("departmentId") Long departmentId);

    @Query("SELECT e FROM Employee e WHERE LOWER(e.email) LIKE LOWER(CONCAT('%', :domain, '%'))")
    List<Employee> findByEmailDomain(@Param("domain") String domain);
}
