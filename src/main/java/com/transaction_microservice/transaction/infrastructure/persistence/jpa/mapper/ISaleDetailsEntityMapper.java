package com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper;

import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SaleDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ISaleDetailsEntityMapper {

    @Mapping(target = "sale", ignore = true)
    SaleDetailsModel saleDetailsEntityToSaleDetailsModel(SaleDetailsEntity saleDetailsEntity);

    @Mapping(target = "sale", source = "sale")
    SaleDetailsEntity saleDetailsModelToSaleDetailsEntity(SaleDetailsModel saleDetailsModel);

}
