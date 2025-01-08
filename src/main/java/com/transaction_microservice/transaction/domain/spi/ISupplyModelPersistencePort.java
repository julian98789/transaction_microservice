package com.transaction_microservice.transaction.domain.spi;

import com.transaction_microservice.transaction.domain.model.SupplyModel;

import java.time.LocalDate;

public interface ISupplyModelPersistencePort {

    void saveSupply(SupplyModel supplyModel);

    LocalDate findNextSupplyDateByArticleId(Long articleId);
}
