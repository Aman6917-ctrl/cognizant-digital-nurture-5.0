package com.cognizant.springlearn.web;

import com.cognizant.springlearn.security.JwtTokenService;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;

    public AuthenticationController(
            AuthenticationManager authenticationManager, JwtTokenService jwtTokenService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenService = jwtTokenService;
    }

    @GetMapping("/authenticate")
    public Map<String, String> authenticate(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String authorization) {
        LOGGER.info("START GET /authenticate");
        if (authorization == null || !authorization.startsWith("Basic ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Basic Authorization header required");
        }
        String decoded = new String(Base64.getDecoder().decode(authorization.substring(6)), StandardCharsets.UTF_8);
        int separator = decoded.indexOf(':');
        if (separator < 0) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Basic credentials format");
        }
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    decoded.substring(0, separator), decoded.substring(separator + 1)));
        } catch (AuthenticationException ex) {
            LOGGER.warn("Authentication failed: {}", ex.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);
        LOGGER.info("END GET /authenticate user={}", userDetails.getUsername());
        return Map.of("token", token);
    }
}

@RestController
class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @GetMapping("/countries")
    public List<Map<String, String>> getCountries() {
        LOGGER.info("START GET /countries");
        return List.of(Map.of("code", "IN", "name", "India"));
    }
}

@RestController
class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @GetMapping("/employees")
    public List<Map<String, Object>> getEmployees() {
        LOGGER.info("START GET /employees");
        return List.of(Map.of("id", 101, "name", "Aisha Khan"));
    }
}

@RestController
class DepartmentController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);

    @GetMapping("/departments")
    public List<Map<String, Object>> getDepartments() {
        LOGGER.info("START GET /departments");
        return List.of(Map.of("id", 1, "name", "Engineering"));
    }
}
