package com.transaction_microservice.transaction.domain.usecase;

import com.transaction_microservice.transaction.domain.model.supply.SupplyModel;
import com.transaction_microservice.transaction.domain.spi.IStockConnectionPersistencePort;
import com.transaction_microservice.transaction.domain.spi.ISupplyModelPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class SupplyModelUseCaseTest {

    @Mock
    private ISupplyModelPersistencePort supplyModelPersistencePort;

    @Mock
    private IStockConnectionPersistencePort stockConnectionPersistencePort;

    @InjectMocks
    private SupplyModelUseCase supplyModelUseCase;

    private SupplyModel supplyModel;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        supplyModel = new SupplyModel();
    }

    @Test
    @DisplayName("Save supply successfully")
    void shouldSaveSupplySuccessfully() {

        supplyModel.setArticleId(1L);
        supplyModel.setQuantity(10);
        supplyModel.setNextSupplyDate(LocalDate.now());

        when(stockConnectionPersistencePort.existById(anyLong())).thenReturn(true);
        doNothing().when(stockConnectionPersistencePort).updateQuantityArticle(anyLong(), any(Integer.class));
        when(supplyModelPersistencePort.saveSupply(any(SupplyModel.class))).thenReturn(supplyModel);

        SupplyModel result = supplyModelUseCase.saveSupply(supplyModel, 1L);

        assertEquals(supplyModel, result);

        verify(stockConnectionPersistencePort, times(1)).existById(anyLong());
        verify(stockConnectionPersistencePort, times(1)).updateQuantityArticle(anyLong(), any(Integer.class));
        verify(supplyModelPersistencePort, times(1)).saveSupply(any(SupplyModel.class));
    }

    @Test
    @DisplayName("Get next supply date successfully")
    void shouldGetNextSupplyDateSuccessfully() {

        when(supplyModelPersistencePort.getSupplyById(anyLong())).thenReturn(supplyModel);

        LocalDate result = supplyModelUseCase.getNextSupplyDate(1L);

        assertEquals(supplyModel.getNextSupplyDate(), result);

        verify(supplyModelPersistencePort, times(1)).getSupplyById(anyLong());
    }
}