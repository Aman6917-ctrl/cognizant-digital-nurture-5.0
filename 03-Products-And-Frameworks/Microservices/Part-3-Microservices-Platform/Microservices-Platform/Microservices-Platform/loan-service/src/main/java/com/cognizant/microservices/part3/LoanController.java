package com.cognizant.microservices.part3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoanController {
    @GetMapping("/loans/{id}")
    public Map<String, String> get(@PathVariable("id") String id) {
        return Map.of("service", "Loan Service", "id", id);
    }
}
