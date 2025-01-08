package com.transaction_microservice.transaction.domain.api;

import com.transaction_microservice.transaction.domain.model.SupplyModel;

import java.time.LocalDate;

public interface ISupplyModelServicePort {

    void saveSupply(SupplyModel supplyModel, Long articleId);

    LocalDate getNextSupplyDate(Long articleId);
}
