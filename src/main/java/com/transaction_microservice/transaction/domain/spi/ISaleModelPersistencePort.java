package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.model.SalesModel;

public interface ISaleModelPersistencePort {

    SalesModel saveSale(SalesModel salesModel);

    SaleDetailsModel saveSaleDetailsModel (SaleDetailsModel saleDetailsModel);
}
