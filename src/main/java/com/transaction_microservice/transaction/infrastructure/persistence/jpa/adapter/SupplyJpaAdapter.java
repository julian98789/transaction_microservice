package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.domain.model.SupplyModel;
import com.transaction_microservice.transaction.domain.spi.ISupplyModelPersistencePort;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SupplyEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISupplyEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISupplyRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SupplyJpaAdapter implements ISupplyModelPersistencePort {

    private final ISupplyRepository supplyRepository;
    private final ISupplyEntityMapper supplyEntityMapper;

    @Override
    public SupplyModel saveSupply(SupplyModel supplyModel) {

        SupplyEntity supplyEntity = supplyEntityMapper.supplyModelToSupplyEntity(supplyModel);
        SupplyEntity savedSupplyEntity = supplyRepository.save(supplyEntity);

        return supplyEntityMapper.supplyEntityToSupplyModel(savedSupplyEntity);

    }

    @Override
    public SupplyModel getSupplyById(Long supplyId) {
        return supplyRepository.findById(supplyId).
                map(supplyEntityMapper::supplyEntityToSupplyModel).orElse(null);
    }


}
