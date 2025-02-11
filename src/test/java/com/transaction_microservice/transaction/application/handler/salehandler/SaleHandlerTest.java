package com.transaction_microservice.transaction.application.handler.salehandler;


import com.transaction_microservice.transaction.application.dto.saledto.SaleReportResponse;
import com.transaction_microservice.transaction.application.mapper.salemapper.ISaleReportResponseMapper;
import com.transaction_microservice.transaction.domain.api.ISaleModelServicePort;
import com.transaction_microservice.transaction.domain.model.sale.SaleReportModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaleHandlerTest {

    @Mock
    private ISaleModelServicePort saleServicePort;

    @Mock
    private ISaleReportResponseMapper saleReportResponseMapper;

    @InjectMocks
    private SaleHandler saleHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should buy items from the cart and verify the sale report")
    void shouldBuyItemsFromCartAndVerifySaleReport() {
        SaleReportModel saleReportModel = new SaleReportModel();
        SaleReportResponse saleReportResponse = new SaleReportResponse();

        when(saleServicePort.buyItemsFromTheCart()).thenReturn(saleReportModel);
        when(saleReportResponseMapper.saleReportModelToSaleReportResponse(saleReportModel)).thenReturn(saleReportResponse);

        SaleReportResponse result = saleHandler.buyItemsFromTheCart();

        assertEquals(saleReportResponse, result);

        verify(saleServicePort).buyItemsFromTheCart();
        verify(saleReportResponseMapper).saleReportModelToSaleReportResponse(saleReportModel);
    }
}