package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.domain.api.ISaleModelServicePort;
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

    private final ISaleModelServicePort saleServicePort;

    @PreAuthorize(Util.ROLE_CLIENT )
    @GetMapping("/buy-cart")
    public ResponseEntity<String> buyCart() {
        saleServicePort.buyCart();
        return ResponseEntity.ok("bien");
    }
}
