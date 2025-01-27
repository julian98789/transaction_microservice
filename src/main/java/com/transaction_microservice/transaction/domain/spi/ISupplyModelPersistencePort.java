package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.supply.SupplyModel;


public interface ISupplyModelPersistencePort {

    SupplyModel saveSupply(SupplyModel supplyModel);

    SupplyModel getSupplyById(Long supplyId);
}
