package com.cognizant.hql.intro.repository;

import com.cognizant.hql.intro.model.Employee;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.salary >= :minSalary ORDER BY e.name")
    List<Employee> findHighEarners(@Param("minSalary") BigDecimal minSalary);

    @Query("SELECT e FROM Employee e JOIN e.department d WHERE d.name = :deptName")
    List<Employee> findByDepartmentName(@Param("deptName") String deptName);

    @Modifying
    @Query("UPDATE Employee e SET e.salary = e.salary * :factor WHERE e.department.name = :deptName")
    int applyRaiseInDepartment(@Param("deptName") String deptName, @Param("factor") BigDecimal factor);

    @Modifying
    @Query("DELETE FROM Employee e WHERE e.permanent = false")
    int deleteTemporaryEmployees();
}
