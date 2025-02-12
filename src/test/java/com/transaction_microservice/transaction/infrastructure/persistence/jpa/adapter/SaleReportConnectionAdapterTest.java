package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.saledto.SaleReportResponse;
import com.transaction_microservice.transaction.application.dto.saledto.SaleRequest;
import com.transaction_microservice.transaction.application.mapper.salemapper.ISaleReportResponseMapper;
import com.transaction_microservice.transaction.application.mapper.salemapper.ISaleRequestMapper;
import com.transaction_microservice.transaction.domain.model.sale.SaleReportModel;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;
import com.transaction_microservice.transaction.infrastructure.http.feign.IReportFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaleReportConnectionAdapterTest {

    @Mock
    private IReportFeignClient iReportFeignClient;

    @Mock
    private ISaleRequestMapper saleRequestMapper;

    @Mock
    private ISaleReportResponseMapper saleReportResponseMapper;

    @InjectMocks
    private SaleReportConnectionAdapter saleReportConnectionAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    @DisplayName("Create Sale Report - should map request, call feign client, and map response")
    void shouldCreateSaleReport() {
        SalesModel salesModel = new SalesModel();
        SaleRequest saleRequest = new SaleRequest();
        SaleReportResponse saleReportResponse = new SaleReportResponse();
        SaleReportModel saleReportModel = new SaleReportModel();

        when(saleRequestMapper.saleModelToSaleRequest(salesModel)).thenReturn(saleRequest);
        when(iReportFeignClient.createSaleReport(saleRequest)).thenReturn(saleReportResponse);
        when(saleReportResponseMapper.saleReportResponseToSaleReportModel(saleReportResponse))
                .thenReturn(saleReportModel);

        SaleReportModel result = saleReportConnectionAdapter.createSaleReport(salesModel);

        assertEquals(saleReportModel, result);

        verify(saleRequestMapper).saleModelToSaleRequest(salesModel);
        verify(iReportFeignClient).createSaleReport(saleRequest);
        verify(saleReportResponseMapper).saleReportResponseToSaleReportModel(saleReportResponse);
    }
}