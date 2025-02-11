package com.transaction_microservice.transaction.application.handler.supplyhandler;

import com.transaction_microservice.transaction.application.dto.supplydto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supplydto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supplydto.SupplyResponse;


public interface ISupplyHandler {

    SupplyResponse saveSupply(SupplyRequest supplyRequest, Long articleId);

    NextSupplyResponse getNextSupplyDate(Long supplyId);
}
