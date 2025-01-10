package com.transaction_microservice.transaction.application.handler;

import com.transaction_microservice.transaction.application.dto.supply_dto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyResponse;


public interface ISupplyHandler {

    SupplyResponse saveSupply(SupplyRequest supplyRequest, Long articleId);

    NextSupplyResponse getNextSupplyDate(Long supplyId);
}
