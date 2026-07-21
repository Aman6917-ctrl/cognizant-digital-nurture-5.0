package com.cognizant.springlearn.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.cognizant.springlearn.security.JwtTokenService;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthorizationFilterMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Test
    void unauthorizedWithoutJwt() throws Exception {
        mockMvc.perform(get("/countries")).andExpect(status().isUnauthorized());
    }

    @Test
    void jwtAuthorizationAllowsCountries() throws Exception {
        String token = obtainToken("user", "pwd");
        mockMvc.perform(get("/countries").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("IN"));
    }

    @Test
    void jwtAuthorizationAllowsDepartments() throws Exception {
        String token = obtainToken("user", "pwd");
        mockMvc.perform(get("/departments").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void forbiddenWhenUserAccessesAdminEmployees() throws Exception {
        String token = obtainToken("user", "pwd");
        mockMvc.perform(get("/employees").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    void adminJwtCanAccessEmployees() throws Exception {
        String token = obtainToken("admin", "pwd");
        mockMvc.perform(get("/employees").header(HttpHeaders.AUTHORIZATION, "Bearer " + token))
                .andExpect(status().isOk());
    }

    @Test
    void invalidJwtReturns401() throws Exception {
        mockMvc.perform(get("/countries").header(HttpHeaders.AUTHORIZATION, "Bearer not-a-valid-jwt"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void expiredJwtReturns401() throws Exception {
        UserDetails user = User.withUsername("user").password("n/a").roles("USER").build();
        String expired = jwtTokenService.generateTokenWithExpiration(user, Instant.now().minusSeconds(60));
        mockMvc.perform(get("/countries").header(HttpHeaders.AUTHORIZATION, "Bearer " + expired))
                .andExpect(status().isUnauthorized());
    }

    private String obtainToken(String username, String password) throws Exception {
        String basic = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
        MvcResult result = mockMvc.perform(get("/authenticate").header(HttpHeaders.AUTHORIZATION, "Basic " + basic))
                .andExpect(status().isOk())
                .andReturn();
        String body = result.getResponse().getContentAsString();
        int start = body.indexOf("\"token\":\"") + 9;
        int end = body.indexOf('"', start);
        return body.substring(start, end);
    }
}
