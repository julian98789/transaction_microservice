package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.SupplyModel;


public interface ISupplyModelPersistencePort {

    SupplyModel saveSupply(SupplyModel supplyModel);

    SupplyModel getSupplyById(Long supplyId);
}
