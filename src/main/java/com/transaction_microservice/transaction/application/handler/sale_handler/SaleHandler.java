package com.transaction_microservice.transaction.application.handler.sale_handler;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleReportResponse;
import com.transaction_microservice.transaction.application.mapper.sale_mapper.ISaleReportResponseMapper;
import com.transaction_microservice.transaction.domain.api.ISaleModelServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SaleHandler implements ISaleHandler {

    private final ISaleModelServicePort saleServicePort;
    private final ISaleReportResponseMapper saleReportResponseMapper;

    @Override
    public SaleReportResponse buyItemsFromTheCart() {

        return saleReportResponseMapper.saleReportModelToSaleReportResponse(
                saleServicePort.buyItemsFromTheCart());
    }
}
