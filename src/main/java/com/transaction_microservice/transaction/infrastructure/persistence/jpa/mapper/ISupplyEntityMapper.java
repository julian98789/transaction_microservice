package com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper;


import com.transaction_microservice.transaction.domain.model.supply.SupplyModel;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SupplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ISupplyEntityMapper {

    SupplyEntity supplyModelToSupplyEntity(SupplyModel supplyModel);

    SupplyModel supplyEntityToSupplyModel(SupplyEntity supplyEntity);

}
