package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.domain.model.supply.SupplyModel;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.entity.SupplyEntity;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.mapper.ISupplyEntityMapper;
import com.transaction_microservice.transaction.infrastructure.persistence.jpa.repository.ISupplyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class SupplyJpaAdapterTest {

    @Mock
    private ISupplyRepository supplyRepository;

    @Mock
    private ISupplyEntityMapper supplyEntityMapper;

    @InjectMocks
    private SupplyJpaAdapter supplyJpaAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Guarda el suministro")
    void testSaveSupply() {
        SupplyModel supplyModel = new SupplyModel();
        SupplyEntity supplyEntity = new SupplyEntity();

        when(supplyEntityMapper.supplyModelToSupplyEntity(supplyModel)).thenReturn(supplyEntity);
        when(supplyRepository.save(supplyEntity)).thenReturn(supplyEntity);
        when(supplyEntityMapper.supplyEntityToSupplyModel(supplyEntity)).thenReturn(supplyModel);

        SupplyModel result = supplyJpaAdapter.saveSupply(supplyModel);

        assertEquals(supplyModel, result);
    }

    @Test
    @DisplayName("Obtiene el suministro por ID")
    void testGetSupplyById() {
        Long supplyId = 1L;
        SupplyEntity supplyEntity = new SupplyEntity();
        SupplyModel supplyModel = new SupplyModel();

        when(supplyRepository.findById(supplyId)).thenReturn(Optional.of(supplyEntity));
        when(supplyEntityMapper.supplyEntityToSupplyModel(supplyEntity)).thenReturn(supplyModel);

        SupplyModel result = supplyJpaAdapter.getSupplyById(supplyId);

        assertEquals(supplyModel, result);
    }
}