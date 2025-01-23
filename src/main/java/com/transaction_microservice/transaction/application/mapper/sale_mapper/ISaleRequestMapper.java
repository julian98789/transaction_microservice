package com.transaction_microservice.transaction.application.mapper.sale_mapper;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleRequest;
import com.transaction_microservice.transaction.domain.model.SalesModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = ISaleDetailsRequestMapper.class)
public interface ISaleRequestMapper {

    @Mapping(target = "saleDetails", source = "saleDetails")
    SaleRequest saleModelToSaleRequest(SalesModel salesModel);

}
