package com.cognizant.springlearn.web;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void putEmployeeSuccess() throws Exception {
        mockMvc.perform(put("/employees")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 101,
                                  "name": "Aisha Khan Updated",
                                  "salary": 95000,
                                  "permanent": true,
                                  "dateOfBirth": "15/03/1990",
                                  "department": { "id": 1, "name": "Engineering" },
                                  "skills": [
                                    { "id": 1, "name": "Java" },
                                    { "id": 2, "name": "Spring" }
                                  ]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Aisha Khan Updated"))
                .andExpect(jsonPath("$.salary").value(95000));
    }

    @Test
    void putEmployeeNotFoundReturns404() throws Exception {
        mockMvc.perform(put("/employees")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 999,
                                  "name": "Ghost User",
                                  "salary": 50000,
                                  "permanent": false,
                                  "dateOfBirth": "01/01/2000",
                                  "department": { "id": 1, "name": "Engineering" },
                                  "skills": [ { "id": 1, "name": "Java" } ]
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void putEmployeeValidationFailureReturns400() throws Exception {
        mockMvc.perform(put("/employees")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                  "id": 101,
                                  "name": "",
                                  "salary": -1,
                                  "permanent": true,
                                  "dateOfBirth": "15/03/1990",
                                  "department": { "id": 1, "name": "Engineering" },
                                  "skills": [ { "id": 1, "name": "Java" } ]
                                }
                                """))
                .andExpect(status().isBadRequest());
    }
}
