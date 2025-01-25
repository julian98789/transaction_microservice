package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.domain.model.SaleDetailsModel;
import com.transaction_microservice.transaction.domain.model.SalesModel;
import com.transaction_microservice.transaction.domain.spi.ISaleModelPersistencePort;
import com.transaction_microservice.transaction.domain.usecase.SaleModelUseCase;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SaleDetailsEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SalesEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleDetailsEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleDetailsRepository;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@RequiredArgsConstructor
public class SaleAdapter implements ISaleModelPersistencePort {

    private final ISaleRepository saleRepository;
    private final ISaleDetailsRepository saleDetailsRepository;
    private final ISaleEntityMapper saleEntityMapper;
    private final ISaleDetailsEntityMapper saleDetailsEntityMapper;

    private static final Logger logger = LoggerFactory.getLogger(SaleAdapter.class);

    @Override
    public SalesModel saveSale(SalesModel salesModel) {
        SalesEntity salesEntity = saleEntityMapper.salesModelToSalesEntity(salesModel);
        salesEntity.setCreationDate(LocalDate.now());
        return saleEntityMapper.salesEntityToSalesModel(saleRepository.save(salesEntity));
    }


    @Override
    public SaleDetailsModel saveSaleDetailsModel(SaleDetailsModel saleDetailsModel) {
        logger.info("Guardando detalles de venta{}", saleDetailsModel.getSale().getId());
        SaleDetailsEntity saleDetailsEntity = saleDetailsEntityMapper.saleDetailsModelToSaleDetailsEntity(saleDetailsModel);

        SaleDetailsEntity saveDetails= saleDetailsRepository.save(saleDetailsEntity);
        logger.info("Guardando detalles de venta entidad{}", saleDetailsEntity.getId());

         SaleDetailsModel modelo = saleDetailsEntityMapper.saleDetailsEntityToSaleDetailsModel(saveDetails);
        logger.info("modelo {}", modelo.getId());
    return modelo;
    }
}
