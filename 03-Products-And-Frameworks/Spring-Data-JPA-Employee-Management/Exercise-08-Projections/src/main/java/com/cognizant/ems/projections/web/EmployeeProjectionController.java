package com.cognizant.ems.projections.web;

import com.cognizant.ems.projections.dto.EmployeeDto;
import com.cognizant.ems.projections.projection.EmployeeSummary;
import com.cognizant.ems.projections.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
public class EmployeeProjectionController {

    private final EmployeeRepository employeeRepository;

    @GetMapping("/summaries")
    public List<EmployeeSummary> summaries() {
        return employeeRepository.findAllProjectedBy();
    }

    @GetMapping("/dtos")
    public List<EmployeeDto> dtos() {
        return employeeRepository.findEmployeeDtos("cognizant");
    }

    @GetMapping("/constructor-dtos")
    public List<EmployeeDto> constructorDtos() {
        return employeeRepository.findAllConstructorDtos();
    }
}
