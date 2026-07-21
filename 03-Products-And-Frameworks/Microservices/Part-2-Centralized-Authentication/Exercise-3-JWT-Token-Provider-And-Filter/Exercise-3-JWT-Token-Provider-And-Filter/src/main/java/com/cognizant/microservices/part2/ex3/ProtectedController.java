package com.cognizant.microservices.part2.ex3;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ProtectedController {

    @GetMapping("/api/secure/data")
    public Map<String, String> data(Authentication authentication) {
        return Map.of("message", "Protected resource", "user", authentication.getName());
    }
}
