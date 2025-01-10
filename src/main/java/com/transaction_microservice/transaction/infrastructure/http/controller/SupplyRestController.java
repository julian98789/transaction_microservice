package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.supply_dto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyResponse;
import com.transaction_microservice.transaction.application.handler.ISupplyHandler;
import com.transaction_microservice.transaction.domain.util.Util;
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

    private final ISupplyHandler supplyHandler;

    @PreAuthorize(Util.ROLE_AUX_BODEGA )
    @PostMapping("/agregar-articulo/{articleId}")
    public ResponseEntity<SupplyResponse> agregarProductToSupply(@Valid @PathVariable Long articleId, @RequestBody SupplyRequest supplyRequest) {

        SupplyResponse supplyResponse = supplyHandler.saveSupply(supplyRequest, articleId);

        return ResponseEntity.status(HttpStatus.CREATED).body(supplyResponse);
    }

    @PreAuthorize(Util.ROLE_AUX_BODEGA )
    @GetMapping("/next-supply-date/{supplyId}")
    ResponseEntity<NextSupplyResponse> getNextSupplyDate(@PathVariable Long supplyId) {

        NextSupplyResponse nextSupplyDate = supplyHandler.getNextSupplyDate(supplyId);

        return ResponseEntity.status(HttpStatus.OK).body(nextSupplyDate);
    }


}
