package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.spi.ISaleDetailModelPersistencePort;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SaleDetailsEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleDetailsEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleDetailsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SaleDetailJpaAdapter implements ISaleDetailModelPersistencePort {

    private final ISaleDetailsRepository saleDetailsRepository;
    private final ISaleDetailsEntityMapper saleDetailsEntityMapper;

    @Override
    public SaleDetailsModel saveSaleDetailsModel(SaleDetailsModel saleDetailsModel) {
        SaleDetailsEntity saleDetailsEntity = saleDetailsEntityMapper.saleDetailsModelToSaleDetailsEntity(saleDetailsModel);

        SaleDetailsEntity saveDetails= saleDetailsRepository.save(saleDetailsEntity);

        return saleDetailsEntityMapper.saleDetailsEntityToSaleDetailsModel(saveDetails);
    }
}
