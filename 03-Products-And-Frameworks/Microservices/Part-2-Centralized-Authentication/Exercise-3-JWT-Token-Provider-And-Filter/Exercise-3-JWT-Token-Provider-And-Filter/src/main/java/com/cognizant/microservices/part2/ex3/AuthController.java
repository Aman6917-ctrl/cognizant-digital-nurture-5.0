package com.cognizant.microservices.part2.ex3;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/api/auth/token")
    public Map<String, String> token(@RequestBody Map<String, String> request) {
        String username = request.getOrDefault("username", "demo-user");
        String token = jwtTokenProvider.generateToken(username, Map.of("scope", "read"));
        return Map.of("accessToken", token, "tokenType", "Bearer");
    }
}
