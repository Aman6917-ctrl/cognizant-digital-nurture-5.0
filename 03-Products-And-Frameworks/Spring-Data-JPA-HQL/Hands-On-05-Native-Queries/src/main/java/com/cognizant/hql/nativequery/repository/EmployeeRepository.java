package com.cognizant.hql.nativequery.repository;

import com.cognizant.hql.nativequery.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> findAllNative();

    @Query(value = "SELECT COUNT(*) FROM employee WHERE permanent = true", nativeQuery = true)
    long countPermanentNative();

    @Modifying
    @Query(value = "UPDATE employee SET salary = salary * :factor WHERE department_id = :deptId", nativeQuery = true)
    int applyNativeRaise(@Param("deptId") Long deptId, @Param("factor") double factor);
}
