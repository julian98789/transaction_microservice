package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.supplydto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supplydto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supplydto.SupplyResponse;
import com.transaction_microservice.transaction.application.handler.supplyhandler.SupplyHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupplyRestControllerTest {

    @InjectMocks
    private SupplyRestController supplyController;

    @Mock
    private SupplyHandler supplyHandler;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private SupplyRequest supplyRequest;
    private SupplyResponse supplyResponse;
    private NextSupplyResponse nextSupplyResponse;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();

        mockMvc = MockMvcBuilders.standaloneSetup(supplyController).build();

         supplyRequest = new SupplyRequest();
         supplyResponse = new SupplyResponse();
         nextSupplyResponse = new NextSupplyResponse();
    }

    @Test
    @DisplayName("Add product to supply - should return 201 Created with JSON response")
    void shouldAddProductToSupply() throws Exception {
        Long articleId = 1L;

        when(supplyHandler.saveSupply(any(SupplyRequest.class), eq(articleId))).thenReturn(supplyResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/supply/agregar-articulo/{articleId}", articleId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supplyRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(supplyResponse)));

        verify(supplyHandler, times(1)).saveSupply(any(SupplyRequest.class), eq(articleId));
    }

    @Test
    @DisplayName("Get next supply date - should return 200 OK with JSON response")
    void shouldGetNextSupplyDate() throws Exception {
        when(supplyHandler.getNextSupplyDate(anyLong())).thenReturn(nextSupplyResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/supply/next-supply-date/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(nextSupplyResponse)));

        verify(supplyHandler, times(1)).getNextSupplyDate(1L);
    }
}
