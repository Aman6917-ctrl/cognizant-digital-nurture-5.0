package com.cognizant.microservices.part3;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GreetController {
    @GetMapping("/greet")
    public Map<String, String> greet() {
        return Map.of("message", "Hello from Greet Service");
    }
}
