package com.transaction_microservice.transaction.application.mapper.salemapper;

import com.transaction_microservice.transaction.application.dto.saledto.SaleRequest;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISaleRequestMapper {

    SaleRequest saleModelToSaleRequest(SalesModel salesModel);

}
