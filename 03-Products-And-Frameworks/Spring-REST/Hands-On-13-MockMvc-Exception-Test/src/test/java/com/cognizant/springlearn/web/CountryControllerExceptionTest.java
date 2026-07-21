package com.cognizant.springlearn.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CountryControllerExceptionTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getInvalidCountryCodeReturns404WithReason() throws Exception {
        mockMvc.perform(get("/countries/ZZ"))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    String errorMessage = result.getResponse().getErrorMessage();
                    String body = result.getResponse().getContentAsString();
                    assertThat(errorMessage + body).contains("Country not found");
                });
    }

    @Test
    void getAnotherInvalidCountryCodeReturns404WithReason() throws Exception {
        mockMvc.perform(get("/countries/INVALID"))
                .andExpect(status().isNotFound())
                .andExpect(result -> {
                    String errorMessage = result.getResponse().getErrorMessage();
                    String body = result.getResponse().getContentAsString();
                    assertThat(errorMessage + body).contains("Country not found");
                });
    }
}
