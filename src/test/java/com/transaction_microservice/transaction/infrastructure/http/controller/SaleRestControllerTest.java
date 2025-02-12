package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transaction_microservice.transaction.application.dto.saledto.SaleReportResponse;
import com.transaction_microservice.transaction.application.handler.salehandler.ISaleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class SaleRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ISaleHandler saleHandler;

    @InjectMocks
    private SaleRestController saleRestController;

    private ObjectMapper objectMapper;
    private SaleReportResponse saleReportResponse;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders.standaloneSetup(saleRestController).build();

        saleReportResponse = new SaleReportResponse();
    }

    @Test
    void buyItems_ShouldReturnSaleReportResponse() throws Exception {

        when(saleHandler.buyItemsFromTheCart()).thenReturn(saleReportResponse);

        mockMvc.perform(post("/api/supply/buy-cart")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(saleReportResponse)));

        verify(saleHandler, times(1)).buyItemsFromTheCart();
    }
}