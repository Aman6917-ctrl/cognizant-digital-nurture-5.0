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
    void postCountrySuccess() throws Exception {
        mockMvc.perform(post("/countries")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {"code":"CA","name":"Canada","displayOrder":10}
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("CA"));
    }

    @Test
    void validationErrorsReturnStructuredJson() throws Exception {
        mockMvc.perform(post("/countries")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {"code":"X","name":""}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.timestamp").exists())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.errors.code").exists());
    }

    @Test
    void invalidJsonReturnsStructuredError() throws Exception {
        mockMvc.perform(post("/countries")
                        .contentType(APPLICATION_JSON)
                        .content("{code:CA}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors.body").exists());
    }

    @Test
    void incorrectNumericFieldReturnsStructuredError() throws Exception {
        mockMvc.perform(post("/countries")
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {"code":"CA","name":"Canada","displayOrder":"not-a-number"}
                                """))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.message").value("Malformed JSON or incompatible field type"));
    }
}
