package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleReportResponse;
import com.transaction_microservice.transaction.application.handler.sale_handler.ISaleHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SaleRestControllerTest {

    @Mock
    private ISaleHandler saleHandler;

    @InjectMocks
    private SaleRestController saleRestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Compra artículos del carrito y verifica la respuesta")
     void testBuyItems() {
        SaleReportResponse saleReportResponse = new SaleReportResponse();
        when(saleHandler.buyItemsFromTheCart()).thenReturn(saleReportResponse);

        ResponseEntity<SaleReportResponse> response = saleRestController.buyItems();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(saleReportResponse, response.getBody());
    }
}