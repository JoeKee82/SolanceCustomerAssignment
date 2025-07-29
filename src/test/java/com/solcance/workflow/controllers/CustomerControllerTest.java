package com.solcance.workflow.controllers;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void shouldReturnSeededUser() throws Exception {
        this.mockMvc.perform(get("/customer/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("userId=1")));
    }

    @Test
    @Order(2)
    void shouldRegisterCustomer() throws Exception {
        this.mockMvc.perform(put("/customer")
                .contentType(APPLICATION_JSON)
                .content("{ \"iban\": \"123456789\", \"balance\": 5000.0, \"currency\": \"EUR\", \"account_status\": \"INACTIVE\" }"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("2")));
    }

    @Test
    @Order(3)
    void shouldReturnAllCustomers() throws Exception {
        this.mockMvc.perform(get("/customer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"userId\":1")))
                .andExpect(content().string(containsString("\"userId\":2")));
    }

    @Test
    @Order(4)
    void shouldOpenCustomerAccount() throws Exception {
        this.mockMvc.perform(patch("/customer/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ACTIVE")));

        this.mockMvc.perform(get("/customer/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ACTIVE")));
    }

    @Test
    @Order(5)
    void shouldDeleteCustomer() throws Exception {
        this.mockMvc.perform(delete("/customer/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("Customer Deleted")));

        this.mockMvc.perform(get("/customer/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Customer not found")));
    }

}
