package com.cognizant.ems.pagination.service;

import com.cognizant.ems.pagination.dto.DepartmentRequest;
import com.cognizant.ems.pagination.entity.Department;
import com.cognizant.ems.pagination.exception.ResourceNotFoundException;
import com.cognizant.ems.pagination.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department findById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found: " + id));
    }

    public Department create(DepartmentRequest request) {
        Department department = Department.builder().name(request.getName()).build();
        return departmentRepository.save(department);
    }

    public Department update(Long id, DepartmentRequest request) {
        Department department = findById(id);
        department.setName(request.getName());
        return departmentRepository.save(department);
    }

    public void delete(Long id) {
        if (!departmentRepository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found: " + id);
        }
        departmentRepository.deleteById(id);
    }
}
