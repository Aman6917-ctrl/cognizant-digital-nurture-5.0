package com.cognizant.hql.permanent.repository;

import com.cognizant.hql.permanent.model.Employee;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT e FROM Employee e WHERE e.permanent = true")
    List<Employee> findPermanentEmployees();

    @Query("SELECT e FROM Employee e LEFT JOIN FETCH e.department WHERE e.permanent = true")
    List<Employee> findPermanentEmployeesWithDepartmentFetch();
}
