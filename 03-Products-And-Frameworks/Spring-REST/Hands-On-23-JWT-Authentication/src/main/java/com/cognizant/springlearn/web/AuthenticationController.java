package com.cognizant.springlearn.web;

import com.cognizant.springlearn.security.JwtTokenService;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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
            LOGGER.warn("Missing or invalid Basic Authorization header");
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Basic Authorization header required");
        }
        String decoded = new String(Base64.getDecoder().decode(authorization.substring(6)), StandardCharsets.UTF_8);
        int separator = decoded.indexOf(':');
        if (separator < 0) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid Basic credentials format");
        }
        String username = decoded.substring(0, separator);
        String password = decoded.substring(separator + 1);
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
        } catch (AuthenticationException ex) {
            LOGGER.warn("Authentication failed for user={}: {}", username, ex.getMessage());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String token = jwtTokenService.generateToken(userDetails);
        LOGGER.info("END GET /authenticate - token issued for user={}", username);
        return Map.of("token", token);
    }
}
