package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.SaleReportModel;
import com.transaction_microservice.transaction.domain.model.SalesModel;

public interface ISaleReportConnectionPersistencePort {

    SaleReportModel createSaleReport(SalesModel salesModel);
}
