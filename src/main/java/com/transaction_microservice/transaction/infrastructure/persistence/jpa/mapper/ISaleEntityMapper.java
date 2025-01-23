package com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper;

import com.transaction_microservice.transaction.domain.model.SalesModel;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SalesEntity;
import org.mapstruct.*;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        uses = ISaleDetailsEntityMapper.class)
public interface ISaleEntityMapper {

    @Mapping(target = "saleDetails", source = "saleDetails")
    SalesModel salesEntityToSalesModel(SalesEntity salesEntity);

    @Mapping(target = "saleDetails", source = "saleDetails")
    @Mapping(target = "saleDetails.sale", ignore = true)
    SalesEntity salesModelToSalesEntity(SalesModel salesModel);
}
