package com.cognizant.springlearn.web;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class UsersAndRolesMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unauthorizedWithoutCredentials() throws Exception {
        mockMvc.perform(get("/countries")).andExpect(status().isUnauthorized());
    }

    @Test
    void userRoleCanAccessCountries() throws Exception {
        mockMvc.perform(get("/countries").with(httpBasic("user", "pwd"))).andExpect(status().isOk());
    }

    @Test
    void adminForbiddenFromCountries() throws Exception {
        mockMvc.perform(get("/countries").with(httpBasic("admin", "pwd"))).andExpect(status().isForbidden());
    }

    @Test
    void adminCanAccessEmployees() throws Exception {
        mockMvc.perform(get("/employees").with(httpBasic("admin", "pwd"))).andExpect(status().isOk());
    }

    @Test
    void userForbiddenFromEmployees() throws Exception {
        mockMvc.perform(get("/employees").with(httpBasic("user", "pwd"))).andExpect(status().isForbidden());
    }
}
