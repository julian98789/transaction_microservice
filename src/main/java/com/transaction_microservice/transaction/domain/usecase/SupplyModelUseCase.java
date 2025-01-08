package com.transaction_microservice.transaction.domain.usecase;

import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.exception.NotFoundException;
import com.transaction_microservice.transaction.domain.model.SupplyModel;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.spi.IStockConnectionPersistencePort;
import com.transaction_microservice.transaction.domain.spi.ISupplyModelPersistencePort;
import com.transaction_microservice.transaction.domain.util.Util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SupplyModelUseCase implements ISupplyModelServicePort {

    private final ISupplyModelPersistencePort supplyModelPersistencePort;
    private final IStockConnectionPersistencePort stockConnectionPersistencePort;
    private final IAuthenticationSecurityPort authenticationSecurityPort;

    public SupplyModelUseCase(ISupplyModelPersistencePort supplyModelPersistencePort, IStockConnectionPersistencePort
            stockConnectionPersistencePort, IAuthenticationSecurityPort authenticationSecurityPort) {
        this.supplyModelPersistencePort = supplyModelPersistencePort;
        this.stockConnectionPersistencePort = stockConnectionPersistencePort;
        this.authenticationSecurityPort = authenticationSecurityPort;
    }

    @Override
    public void saveSupply(SupplyModel supplyModel, Long articleId) {
        supplyModel.setArticleId(articleId);
        if (!stockConnectionPersistencePort.existById(articleId)) {
            throw new NotFoundException(Util.ARTICLE_NOT_FOUND);
        }
        if (supplyModel.getNextSupplyDate() == null) {
            throw new NotFoundException(Util.NEXT_SUPPLY_DATE_NOT_FOUND);
        }

        supplyModel.setCreationDate(LocalDateTime.now());
        Long userId = authenticationSecurityPort.getAuthenticatedUserId();
        supplyModel.setUserId(userId);
        stockConnectionPersistencePort.updateQuantityArticle(supplyModel.getArticleId(), supplyModel.getQuantity());
        supplyModelPersistencePort.saveSupply(supplyModel);

    }

    @Override
    public LocalDate getNextSupplyDate(Long articleId) {
        return supplyModelPersistencePort.findNextSupplyDateByArticleId(articleId);
    }
}
