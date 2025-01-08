package com.transaction_microservice.transaction.infrastructure.output.jpa.mapper;


import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyRequest;
import com.transaction_microservice.transaction.domain.model.SupplyModel;
import com.transaction_microservice.transaction.infrastructure.output.jpa.entity.SupplyEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface ISupplyEntityMapper {

    SupplyEntity supplyModelToSupplyEntity(SupplyModel supplyModel);

    SupplyModel supplyRequestToSupplyModel(SupplyRequest supplyRequest);
}
