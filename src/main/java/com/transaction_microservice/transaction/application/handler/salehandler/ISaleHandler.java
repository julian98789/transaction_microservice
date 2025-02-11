package com.transaction_microservice.transaction.application.handler.salehandler;

import com.transaction_microservice.transaction.application.dto.saledto.SaleReportResponse;

public interface ISaleHandler {

    SaleReportResponse buyItemsFromTheCart();
}
