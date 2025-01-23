package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.SalesModel;

public interface ISaleReportConnectionPersistencePort {

    void createSaleReport(SalesModel salesModel);
}
