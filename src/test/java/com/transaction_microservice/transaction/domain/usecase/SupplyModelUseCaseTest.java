package com.transaction_microservice.transaction.domain.usecase;

import com.transaction_microservice.transaction.domain.model.SupplyModel;
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
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

public class SupplyModelUseCaseTest {

    @Mock
    private ISupplyModelPersistencePort supplyModelPersistencePort;

    @Mock
    private IStockConnectionPersistencePort stockConnectionPersistencePort;

    @InjectMocks
    private SupplyModelUseCase supplyModelUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Guardar suministro exitosamente")
     void testSaveSupplySuccess() {
        SupplyModel supplyModel = new SupplyModel(1L, 1L, 10, 1L, LocalDate.now().plusDays(1), LocalDate.now());

        when(stockConnectionPersistencePort.existById(anyLong())).thenReturn(true);
        doNothing().when(stockConnectionPersistencePort).updateQuantityArticle(anyLong(), any(Integer.class));
        when(supplyModelPersistencePort.saveSupply(any(SupplyModel.class))).thenReturn(supplyModel);

        SupplyModel result = supplyModelUseCase.saveSupply(supplyModel, 1L);

        assertEquals(supplyModel, result);
    }



    @Test
    @DisplayName("Obtener próxima fecha de suministro exitosamente")
    void testGetNextSupplyDateSuccess() {
        SupplyModel supplyModel = new SupplyModel(1L, 1L, 10, 1L, LocalDate.now().plusDays(1), LocalDate.now());

        when(supplyModelPersistencePort.getSupplyById(anyLong())).thenReturn(supplyModel);

        LocalDate result = supplyModelUseCase.getNextSupplyDate(1L);

        assertEquals(supplyModel.getNextSupplyDate(), result);
    }


}