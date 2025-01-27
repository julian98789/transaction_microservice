package com.transaction_microservice.transaction.domain.api;

import com.transaction_microservice.transaction.domain.model.supply.SupplyModel;

import java.time.LocalDate;

public interface ISupplyModelServicePort {

    SupplyModel saveSupply(SupplyModel supplyModel, Long articleId);

    LocalDate getNextSupplyDate(Long supplyId);
}
