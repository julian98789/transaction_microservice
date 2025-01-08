package com.transaction_microservice.transaction.infrastructure.inpul.controller;

import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyResponse;
import com.transaction_microservice.transaction.application.mapper.supply_mapper.ISupplyResponseMapper;
import com.transaction_microservice.transaction.domain.api.ISupplyModelServicePort;
import com.transaction_microservice.transaction.domain.model.SupplyModel;
import com.transaction_microservice.transaction.domain.util.Util;
import com.transaction_microservice.transaction.infrastructure.output.jpa.mapper.ISupplyEntityMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SupplyRestController {

    private final ISupplyEntityMapper supplyEntityMapper;
    private final ISupplyModelServicePort supplyModelServicePort;
    private final ISupplyResponseMapper supplyResponseMapper;

    @PreAuthorize(Util.ROLE_AUX_BODEGA )
    @PostMapping("/add/{articleId}")
    public ResponseEntity<SupplyResponse> addProductToSupply(@Valid @PathVariable Long articleId,  @RequestBody SupplyRequest supplyRequest) {
        SupplyModel supplyModel = supplyEntityMapper.supplyRequestToSupplyModel(supplyRequest);

        supplyModelServicePort.saveSupply(supplyModel, articleId);
        SupplyResponse supplyResponse = supplyResponseMapper.supplyModelToSupplyResponse(supplyModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(supplyResponse);
    }
}
