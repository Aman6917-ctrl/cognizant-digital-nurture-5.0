package com.cognizant.microservices.part2.ex2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SecureApiController {

    @GetMapping("/api/public/status")
    public Map<String, String> status() {
        return Map.of("status", "OK");
    }

    @GetMapping("/api/secure/profile")
    public Map<String, Object> profile(@AuthenticationPrincipal Jwt jwt) {
        return Map.of("subject", jwt.getSubject(), "scope", jwt.getClaimAsString("scope"));
    }
}
