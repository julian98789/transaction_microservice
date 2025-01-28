package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleReportResponse;
import com.transaction_microservice.transaction.application.dto.sale_dto.SaleRequest;
import com.transaction_microservice.transaction.application.mapper.sale_mapper.ISaleReportResponseMapper;
import com.transaction_microservice.transaction.application.mapper.sale_mapper.ISaleRequestMapper;
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
import static org.mockito.Mockito.when;

class ISaleReportConnectionAdapterTest {

    @Mock
    private IReportFeignClient iReportFeignClient;

    @Mock
    private ISaleRequestMapper saleRequestMapper;

    @Mock
    private ISaleReportResponseMapper saleReportResponseMapper;

    @InjectMocks
    private ISaleReportConnectionAdapter saleReportConnectionAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Crea un reporte de ventas")
    void testCreateSaleReport() {
        SalesModel salesModel = new SalesModel();
        SaleRequest saleRequest = new SaleRequest();
        SaleReportResponse saleReportResponse = new SaleReportResponse();
        SaleReportModel saleReportModel = new SaleReportModel();

        when(saleRequestMapper.saleModelToSaleRequest(salesModel)).thenReturn(saleRequest);
        when(iReportFeignClient.createSaleReport(saleRequest)).thenReturn(saleReportResponse);
        when(saleReportResponseMapper.saleReportResponseToSaleReportModel(saleReportResponse)).thenReturn(saleReportModel);

        SaleReportModel result = saleReportConnectionAdapter.createSaleReport(salesModel);

        assertEquals(saleReportModel, result);
    }
}