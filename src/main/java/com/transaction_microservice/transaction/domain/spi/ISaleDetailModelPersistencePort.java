package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;

public interface ISaleDetailModelPersistencePort {

    SaleDetailsModel saveSaleDetailsModel (SaleDetailsModel saleDetailsModel);
}
