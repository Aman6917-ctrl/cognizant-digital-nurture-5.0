package com.cognizant.relationships.repository;

import com.cognizant.relationships.model.Employee;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.skills WHERE e.id = :id")
    Optional<Employee> findByIdWithSkills(@Param("id") Long id);
}
