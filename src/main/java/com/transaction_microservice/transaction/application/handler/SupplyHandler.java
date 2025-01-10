package com.transaction_microservice.transaction.application.handler;

import com.transaction_microservice.transaction.application.dto.supply_dto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyResponse;
import com.transaction_microservice.transaction.application.mapper.supply_mapper.ISupplyRequestMapper;
import com.transaction_microservice.transaction.application.mapper.supply_mapper.ISupplyResponseMapper;
import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.model.SupplyModel;
import com.transaction_microservice.transaction.domain.security.IAuthenticationSecurityPort;
import com.transaction_microservice.transaction.domain.util.Util;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Transactional
public class SupplyHandler implements ISupplyHandler {

    private final ISupplyRequestMapper supplyEntityMapper;
    private final ISupplyModelServicePort supplyModelServicePort;
    private final ISupplyResponseMapper supplyResponseMapper;
    private final IAuthenticationSecurityPort authenticationSecurityPort;

    @Override
    public SupplyResponse saveSupply(SupplyRequest supplyRequest, Long articleId) {

        SupplyModel supplyModel = supplyEntityMapper.supplyRequestToSupplyModel(supplyRequest);
        supplyModel.setArticleId(articleId);
        supplyModel.setCreationDate(LocalDate.now());
        Long userId = authenticationSecurityPort.getAuthenticatedUserId();
        supplyModel.setUserId(userId);
        SupplyModel saveSupply = supplyModelServicePort.saveSupply(supplyModel, articleId);

        return supplyResponseMapper.supplyModelToSupplyResponse(saveSupply);
    }

    @Override
    public NextSupplyResponse getNextSupplyDate(Long supplyId) {
        LocalDate nextSupplyDate = supplyModelServicePort.getNextSupplyDate(supplyId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Util.DATE_FORMAT);

        NextSupplyResponse nextSupplyResponse = new NextSupplyResponse();
        nextSupplyResponse.setNextSupplyDate(nextSupplyDate.format(formatter));

        return nextSupplyResponse;
    }
}
