package com.transaction_microservice.transaction.application.mapper.supply_mapper;

import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyResponse;
import com.transaction_microservice.transaction.domain.model.SupplyModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ISupplyResponseMapper {

    SupplyResponse supplyModelToSupplyResponse(SupplyModel supplyModel);
}
