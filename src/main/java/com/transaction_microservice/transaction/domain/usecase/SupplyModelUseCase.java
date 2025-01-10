package com.transaction_microservice.transaction.domain.usecase;

import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.exception.InvalidSupplyDateException;
import com.transaction_microservice.transaction.domain.exception.NotFoundException;
import com.transaction_microservice.transaction.domain.model.SupplyModel;
import com.transaction_microservice.transaction.domain.spi.IStockConnectionPersistencePort;
import com.transaction_microservice.transaction.domain.spi.ISupplyModelPersistencePort;
import com.transaction_microservice.transaction.domain.util.Util;

import java.time.LocalDate;


public class SupplyModelUseCase implements ISupplyModelServicePort {

    private final ISupplyModelPersistencePort supplyModelPersistencePort;
    private final IStockConnectionPersistencePort stockConnectionPersistencePort;

    public SupplyModelUseCase(ISupplyModelPersistencePort supplyModelPersistencePort, IStockConnectionPersistencePort stockConnectionPersistencePort) {
        this.supplyModelPersistencePort = supplyModelPersistencePort;
        this.stockConnectionPersistencePort = stockConnectionPersistencePort;
    }

    @Override
    public SupplyModel saveSupply(SupplyModel supplyModel, Long articleId) {

        if (!stockConnectionPersistencePort.existById(articleId)) {
            throw new NotFoundException(Util.ARTICLE_NOT_FOUND);
        }
        if (supplyModel.getNextSupplyDate().isBefore(LocalDate.now())) {
            throw new InvalidSupplyDateException(Util.NEXT_SUPPLY_DATE_INVALID);
        }

        stockConnectionPersistencePort.updateQuantityArticle(supplyModel.getArticleId(), supplyModel.getQuantity());

        return supplyModelPersistencePort.saveSupply(supplyModel);
    }

    @Override
    public LocalDate getNextSupplyDate(Long supplyId) {

        SupplyModel supplyModel = supplyModelPersistencePort.getSupplyById(supplyId);

        if (supplyModel == null) {
            throw new NotFoundException(Util.SUPPLY_NOT_FOUND);
        }

        return supplyModel.getNextSupplyDate();
    }
}
