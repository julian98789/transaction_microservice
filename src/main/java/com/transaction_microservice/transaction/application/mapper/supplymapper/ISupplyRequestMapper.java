package com.transaction_microservice.transaction.application.mapper.supplymapper;

import com.transaction_microservice.transaction.application.dto.supplydto.SupplyRequest;
import com.transaction_microservice.transaction.domain.model.supply.SupplyModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyRequestMapper {
    SupplyModel supplyRequestToSupplyModel(SupplyRequest supplyRequest);
}

