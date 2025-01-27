package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.model.sale.SalesModel;
import com.transaction_microservice.transaction.domain.spi.ISaleModelPersistencePort;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SaleDetailsEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SalesEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleDetailsEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleDetailsRepository;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class SaleJpaAdapter implements ISaleModelPersistencePort {

    private final ISaleRepository saleRepository;
    private final ISaleEntityMapper saleEntityMapper;


    @Override
    public SalesModel saveSale(SalesModel salesModel) {
        SalesEntity salesEntity = saleEntityMapper.salesModelToSalesEntity(salesModel);
        return saleEntityMapper.salesEntityToSalesModel(saleRepository.save(salesEntity));
    }
}
