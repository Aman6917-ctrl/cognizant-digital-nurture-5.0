package com.cognizant.springlearn.web;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/employees")
    public List<Map<String, Object>> getEmployees() {
        LOGGER.info("START GET /employees controller (ADMIN)");
        List<Map<String, Object>> employees = List.of(Map.of("id", 101, "name", "Aisha Khan"));
        LOGGER.info("END GET /employees controller");
        return employees;
    }
}
