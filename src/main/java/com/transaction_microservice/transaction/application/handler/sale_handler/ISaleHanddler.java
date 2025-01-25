package com.transaction_microservice.transaction.application.handler.sale_handler;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleReportResponse;

public interface ISaleHanddler {

    SaleReportResponse buyItemsFromTheCart();
}
