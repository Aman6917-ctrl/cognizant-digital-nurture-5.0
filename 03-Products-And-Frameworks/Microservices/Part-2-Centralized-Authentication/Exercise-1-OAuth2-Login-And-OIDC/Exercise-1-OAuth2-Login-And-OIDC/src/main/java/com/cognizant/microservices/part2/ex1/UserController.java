package com.cognizant.microservices.part2.ex1;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OidcUser oidcUser) {
        if (oidcUser == null) {
            return Map.of("authenticated", false);
        }
        return Map.of(
                "authenticated", true,
                "subject", oidcUser.getSubject(),
                "email", oidcUser.getEmail(),
                "name", oidcUser.getFullName());
    }
}
