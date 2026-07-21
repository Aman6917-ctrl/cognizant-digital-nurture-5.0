package com.cognizant.springlearn.web;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiInfoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiInfoController.class);

    @GetMapping("/health")
    public Map<String, String> health() {
        LOGGER.debug("Health check requested");
        return Map.of("status", "UP");
    }

    @GetMapping("/info")
    public Map<String, String> info() {
        LOGGER.debug("Info endpoint requested");
        return Map.of(
                "module", "Hands-On-01-Spring-Web-Project",
                "stack", "Spring Boot 3.3.x + Spring Web + Embedded Tomcat");
    }
}
