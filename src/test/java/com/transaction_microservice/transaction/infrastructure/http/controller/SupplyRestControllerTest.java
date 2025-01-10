package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.supply_dto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyResponse;
import com.transaction_microservice.transaction.application.handler.ISupplyHandler;
import com.transaction_microservice.transaction.infrastructure.http.controller.SupplyRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class SupplyRestControllerTest {

    @Mock
    private ISupplyHandler supplyHandler;

    @InjectMocks
    private SupplyRestController supplyRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Agregar producto al suministro")
     void testAgregarProductToSupply() {
        SupplyRequest supplyRequest = new SupplyRequest();
        SupplyResponse supplyResponse = new SupplyResponse();

        when(supplyHandler.saveSupply(any(SupplyRequest.class), anyLong())).thenReturn(supplyResponse);

        ResponseEntity<SupplyResponse> response = supplyRestController.agregarProductToSupply(1L, supplyRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(supplyResponse, response.getBody());
    }

    @Test
    @DisplayName("Obtener la próxima fecha de suministro")
     void testGetNextSupplyDate() {
        NextSupplyResponse nextSupplyResponse = new NextSupplyResponse();

        when(supplyHandler.getNextSupplyDate(anyLong())).thenReturn(nextSupplyResponse);

        ResponseEntity<NextSupplyResponse> response = supplyRestController.getNextSupplyDate(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(nextSupplyResponse, response.getBody());
    }
}