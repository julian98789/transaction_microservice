package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.sale.SaleReportModel;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;

public interface ISaleReportConnectionPersistencePort {

    SaleReportModel createSaleReport(SalesModel salesModel);
}
