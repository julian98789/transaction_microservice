package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.domain.model.sale.SaleDetailsModel;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SaleDetailsEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleDetailsEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleDetailsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SaleDetailJpaAdapterTest {

    @Mock
    private ISaleDetailsRepository saleDetailsRepository;

    @Mock
    private ISaleDetailsEntityMapper saleDetailsEntityMapper;

    @InjectMocks
    private SaleDetailJpaAdapter saleDetailJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Guarda el modelo de detalles de venta")
    void testSaveSaleDetailsModel() {
        SaleDetailsModel saleDetailsModel = new SaleDetailsModel();
        SaleDetailsEntity saleDetailsEntity = new SaleDetailsEntity();
        SaleDetailsEntity savedSaleDetailsEntity = new SaleDetailsEntity();

        when(saleDetailsEntityMapper.saleDetailsModelToSaleDetailsEntity(saleDetailsModel)).thenReturn(saleDetailsEntity);
        when(saleDetailsRepository.save(saleDetailsEntity)).thenReturn(savedSaleDetailsEntity);
        when(saleDetailsEntityMapper.saleDetailsEntityToSaleDetailsModel(savedSaleDetailsEntity)).thenReturn(saleDetailsModel);

        SaleDetailsModel result = saleDetailJpaAdapter.saveSaleDetailsModel(saleDetailsModel);

        assertEquals(saleDetailsModel, result);
    }
}