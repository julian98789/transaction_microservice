package com.transaction_microservice.transaction.application.mapper.supplymapper;

import com.transaction_microservice.transaction.application.dto.supplydto.SupplyResponse;
import com.transaction_microservice.transaction.domain.model.supply.SupplyModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyResponseMapper {

    SupplyResponse supplyModelToSupplyResponse(SupplyModel supplyModel);
}
