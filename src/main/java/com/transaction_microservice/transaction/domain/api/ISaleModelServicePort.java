package com.transaction_microservice.transaction.domain.api;

import com.transaction_microservice.transaction.domain.model.SaleReportModel;

public interface ISaleModelServicePort {

    SaleReportModel buyItemsFromTheCart();
}
