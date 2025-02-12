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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SupplyJpaAdapterTest {

    @Mock
    private ISupplyRepository supplyRepository;

    @Mock
    private ISupplyEntityMapper supplyEntityMapper;

    @InjectMocks
    private SupplyJpaAdapter supplyJpaAdapter;

    private SupplyModel supplyModel;
    private SupplyEntity supplyEntity;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        supplyModel = new SupplyModel();
        supplyEntity = new SupplyEntity();
    }

    @Test
    @DisplayName("Save Supply - should save and return the saved SupplyModel")
    void shouldSaveAndReturnSupplyModel() {
        when(supplyEntityMapper.supplyModelToSupplyEntity(supplyModel)).thenReturn(supplyEntity);
        when(supplyRepository.save(supplyEntity)).thenReturn(supplyEntity);
        when(supplyEntityMapper.supplyEntityToSupplyModel(supplyEntity)).thenReturn(supplyModel);

        SupplyModel result = supplyJpaAdapter.saveSupply(supplyModel);

        assertEquals(supplyModel, result);

        verify(supplyEntityMapper).supplyModelToSupplyEntity(supplyModel);
        verify(supplyRepository).save(supplyEntity);
        verify(supplyEntityMapper).supplyEntityToSupplyModel(supplyEntity);
    }

    @Test
    @DisplayName("Get Supply by ID - should return the SupplyModel for the given ID")
    void shouldReturnSupplyModelById() {
        Long supplyId = 1L;

        when(supplyRepository.findById(supplyId)).thenReturn(Optional.of(supplyEntity));
        when(supplyEntityMapper.supplyEntityToSupplyModel(supplyEntity)).thenReturn(supplyModel);

        SupplyModel result = supplyJpaAdapter.getSupplyById(supplyId);

        assertEquals(supplyModel, result);

        verify(supplyRepository).findById(supplyId);
        verify(supplyEntityMapper).supplyEntityToSupplyModel(supplyEntity);
    }
}