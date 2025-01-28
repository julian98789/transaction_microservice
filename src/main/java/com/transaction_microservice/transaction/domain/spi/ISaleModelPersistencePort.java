package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;

public interface ISaleModelPersistencePort {

    SalesModel saveSale(SalesModel salesModel);


}
