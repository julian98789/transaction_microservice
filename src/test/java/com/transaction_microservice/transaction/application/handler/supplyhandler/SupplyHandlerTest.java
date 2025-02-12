package com.transaction_microservice.transaction.application.handler.supplyhandler;

import com.transaction_microservice.transaction.application.dto.supplydto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supplydto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supplydto.SupplyResponse;
import com.transaction_microservice.transaction.application.mapper.supplymapper.ISupplyRequestMapper;
import com.transaction_microservice.transaction.application.mapper.supplymapper.ISupplyResponseMapper;
import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.model.supply.SupplyModel;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.util.Util;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SupplyHandlerTest {

    @Mock
    private ISupplyRequestMapper supplyRequestMapper;

    @Mock
    private ISupplyModelServicePort supplyModelServicePort;

    @Mock
    private ISupplyResponseMapper supplyResponseMapper;

    @Mock
    private IAuthenticationSecurityPort authenticationSecurityPort;

    @InjectMocks
    private SupplyHandler supplyHandler;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

     @Test
     @DisplayName("Should save supply successfully")
     void shouldSaveSupplySuccessfully() {
         SupplyRequest supplyRequest = new SupplyRequest();
         SupplyModel supplyModel = new SupplyModel();
         SupplyResponse supplyResponse = new SupplyResponse();

         when(supplyRequestMapper.supplyRequestToSupplyModel(any(SupplyRequest.class))).thenReturn(supplyModel);
         when(authenticationSecurityPort.getAuthenticatedUserId()).thenReturn(1L);
         when(supplyModelServicePort.saveSupply(any(SupplyModel.class), anyLong())).thenReturn(supplyModel);
         when(supplyResponseMapper.supplyModelToSupplyResponse(any(SupplyModel.class))).thenReturn(supplyResponse);

         SupplyResponse result = supplyHandler.saveSupply(supplyRequest, 1L);

         assertEquals(supplyResponse, result);

         verify(supplyRequestMapper).supplyRequestToSupplyModel(any(SupplyRequest.class));
         verify(authenticationSecurityPort).getAuthenticatedUserId();
         verify(supplyModelServicePort).saveSupply(any(SupplyModel.class), anyLong());
         verify(supplyResponseMapper).supplyModelToSupplyResponse(any(SupplyModel.class));
     }

     @Test
     @DisplayName("Should get next supply date successfully")
     void shouldGetNextSupplyDateSuccessfully() {
         LocalDate nextSupplyDate = LocalDate.now();
         NextSupplyResponse nextSupplyResponse = new NextSupplyResponse();
         nextSupplyResponse.setNextSupplyDate(nextSupplyDate.format(DateTimeFormatter.ofPattern(Util.DATE_FORMAT)));

         when(supplyModelServicePort.getNextSupplyDate(anyLong())).thenReturn(nextSupplyDate);

         NextSupplyResponse result = supplyHandler.getNextSupplyDate(1L);

         assertEquals(nextSupplyResponse.getNextSupplyDate(), result.getNextSupplyDate());

         verify(supplyModelServicePort).getNextSupplyDate(anyLong());
     }
 }