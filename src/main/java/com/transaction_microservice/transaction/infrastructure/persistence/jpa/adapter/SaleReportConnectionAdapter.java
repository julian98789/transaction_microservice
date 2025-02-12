package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.saledto.SaleReportResponse;
import com.transaction_microservice.transaction.application.dto.saledto.SaleRequest;
import com.transaction_microservice.transaction.application.mapper.salemapper.ISaleReportResponseMapper;
import com.transaction_microservice.transaction.application.mapper.salemapper.ISaleRequestMapper;
import com.transaction_microservice.transaction.domain.model.sale.SaleReportModel;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;
import com.transaction_microservice.transaction.domain.spi.ISaleReportConnectionPersistencePort;
import com.transaction_microservice.transaction.infrastructure.http.feign.IReportFeignClient;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@AllArgsConstructor
public class SaleReportConnectionAdapter implements ISaleReportConnectionPersistencePort {

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

