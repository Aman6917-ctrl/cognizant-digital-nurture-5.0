package com.cognizant.hql.aggregate.repository;

import com.cognizant.hql.aggregate.model.Employee;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.department.name = :deptName")
    BigDecimal averageSalaryByDepartmentName(@Param("deptName") String deptName);

    @Query("SELECT e.department.name, COUNT(e) FROM Employee e GROUP BY e.department.name ORDER BY e.department.name")
    List<Object[]> countEmployeesByDepartment();

    @Query("SELECT MAX(e.salary) FROM Employee e WHERE e.department.id = :deptId")
    BigDecimal maxSalary(@Param("deptId") Long deptId);

    @Query("SELECT MIN(e.salary) FROM Employee e WHERE e.department.id = :deptId")
    BigDecimal minSalary(@Param("deptId") Long deptId);

    @Query("SELECT SUM(e.salary) FROM Employee e WHERE e.permanent = true")
    BigDecimal sumSalaryForPermanentEmployees();
}
