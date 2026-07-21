package com.cognizant.springlearn.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class JwtAuthenticationMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void authenticateWithoutHeaderReturns401() throws Exception {
        mockMvc.perform(get("/authenticate")).andExpect(status().isUnauthorized());
    }

    @Test
    void jwtAuthenticationWithBasicCredentials() throws Exception {
        String basic = Base64.getEncoder().encodeToString("user:pwd".getBytes(StandardCharsets.UTF_8));
        mockMvc.perform(get("/authenticate").header(HttpHeaders.AUTHORIZATION, "Basic " + basic))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").isNotEmpty());
    }

    @Test
    void authenticateWithInvalidCredentialsReturns401() throws Exception {
        String basic = Base64.getEncoder().encodeToString("user:wrong".getBytes(StandardCharsets.UTF_8));
        mockMvc.perform(get("/authenticate").header(HttpHeaders.AUTHORIZATION, "Basic " + basic))
                .andExpect(status().isUnauthorized());
    }
}
