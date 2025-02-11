package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.domain.model.sale.SalesModel;;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SalesEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISaleEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISaleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SaleJpaAdapterTest {

    @Mock
    private ISaleRepository saleRepository;

    @Mock
    private ISaleEntityMapper saleEntityMapper;

    @InjectMocks
    private SaleJpaAdapter saleJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Save Sale - should save and return the saved SalesModel")
    void shouldSaveAndReturnSalesModel() {
        SalesModel salesModel = new SalesModel();
        SalesEntity salesEntity = new SalesEntity();

        when(saleEntityMapper.salesModelToSalesEntity(salesModel)).thenReturn(salesEntity);
        when(saleRepository.save(salesEntity)).thenReturn(salesEntity);
        when(saleEntityMapper.salesEntityToSalesModel(salesEntity)).thenReturn(salesModel);

        SalesModel result = saleJpaAdapter.saveSale(salesModel);

        assertEquals(salesModel, result);

        verify(saleEntityMapper).salesModelToSalesEntity(salesModel);
        verify(saleRepository).save(salesEntity);
        verify(saleEntityMapper).salesEntityToSalesModel(salesEntity);
    }
}