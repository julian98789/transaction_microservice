package com.transaction_microservice.transaction.infrastructure.output.jpa.adapter;

import com.transaction_microservice.transaction.domain.model.SupplyModel;
import com.transaction_microservice.transaction.domain.spi.ISupplyModelPersistencePort;
import com.transaction_microservice.transaction.infrastructure.output.jpa.mapper.ISupplyEntityMapper;
import com.transaction_microservice.transaction.infrastructure.output.jpa.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
public class SupplyJpaAdapter implements ISupplyModelPersistencePort {

    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;

    @Override
    public void saveSupply(SupplyModel supplyModel) {
        supplyRepository.save(supplyEntityMapper.supplyModelToSupplyEntity(supplyModel));
    }

    @Override
    public LocalDate findNextSupplyDateByArticleId(Long articleId) {
        return supplyRepository.findNextSupplyDateByArticleId(articleId);
    }
}
