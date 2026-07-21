package com.cognizant.springlearn.web;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void postCountrySuccessMapsJsonToJavaAndReturnsCreated() throws Exception {
        mockMvc.perform(post("/countries")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {"code":"FR","name":"France"}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("FR"))
                .andExpect(jsonPath("$.name").value("France"));
    }
}
