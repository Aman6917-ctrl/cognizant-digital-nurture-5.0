package com.cognizant.ems.projections.repository;

import com.cognizant.ems.projections.dto.EmployeeDto;
import com.cognizant.ems.projections.entity.Employee;
import com.cognizant.ems.projections.projection.EmployeeSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<EmployeeSummary> findAllProjectedBy();

    @Query("SELECT new com.cognizant.ems.projections.dto.EmployeeDto(e.name, e.email) FROM Employee e WHERE e.email LIKE CONCAT('%', :part, '%')")
    List<EmployeeDto> findEmployeeDtos(@org.springframework.data.repository.query.Param("part") String part);

    @Query("SELECT new com.cognizant.ems.projections.dto.EmployeeDto(e.name, e.email) FROM Employee e")
    List<EmployeeDto> findAllConstructorDtos();
}
