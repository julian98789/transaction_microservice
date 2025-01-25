package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleReportResponse;
import com.transaction_microservice.transaction.application.handler.sale_handler.ISaleHanddler;
import com.transaction_microservice.transaction.domain.util.Util;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SaleRestController {

    private final ISaleHanddler saleHanddler;

    @PreAuthorize(Util.ROLE_CLIENT )
    @GetMapping("/buy-cart")
    public ResponseEntity<SaleReportResponse> buyItems() {

        return ResponseEntity.ok(saleHanddler.buyItemsFromTheCart());
    }
}
