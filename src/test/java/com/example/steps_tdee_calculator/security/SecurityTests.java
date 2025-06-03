package com.example.steps_tdee_calculator.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "user", roles = {"USER"})
    public void testIfDeniedUserAccessToApi() throws Exception {
        mockMvc.perform(post("/api/users/getAll"))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testIfDeniedAccessToUserHomePageWhenAnonymous() throws Exception {
        mockMvc.perform(post("/userHomePage"))
                .andExpect(status().isForbidden());
    }
}
