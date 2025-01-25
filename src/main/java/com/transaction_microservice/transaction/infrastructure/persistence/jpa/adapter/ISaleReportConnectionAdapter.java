package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleReportResponse;
import com.transaction_microservice.transaction.application.dto.sale_dto.SaleRequest;
import com.transaction_microservice.transaction.application.dto.sale_dto.SaleDetailsRequest;
import com.transaction_microservice.transaction.application.mapper.sale_mapper.ISaleReportResponseMapper;
import com.transaction_microservice.transaction.application.mapper.sale_mapper.ISaleRequestMapper;
import com.transaction_microservice.transaction.domain.model.SaleReportModel;
import com.transaction_microservice.transaction.domain.model.SalesModel;
import com.transaction_microservice.transaction.domain.spi.ISaleReportConnectionPersistencePort;
import com.transaction_microservice.transaction.infrastructure.http.feign.IReportFeignClient;
import lombok.AllArgsConstructor;


@AllArgsConstructor
public class ISaleReportConnectionAdapter implements ISaleReportConnectionPersistencePort {

    private final IReportFeignClient iReportFeignClient;
    private final ISaleRequestMapper saleRequestMapper;
    private final ISaleReportResponseMapper saleReportResponseMapper;


    @Override
    public SaleReportModel createSaleReport(SalesModel salesModel) {

        SaleRequest saleRequest = saleRequestMapper.saleModelToSaleRequest(salesModel);

        SaleReportResponse saleReportResponse = iReportFeignClient.createSaleReport(saleRequest);

        return saleReportResponseMapper.saleReportResponseToSaleReportModel(saleReportResponse);
    }
}

