package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.sale_dto.SaleReportResponse;
import com.transaction_microservice.transaction.application.handler.sale_handler.ISaleHanddler;
import com.transaction_microservice.transaction.domain.util.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SaleRestController {

    private final ISaleHanddler saleHandler;

    @Operation(summary = "Comprar artículos del carrito", description = "Este endpoint permite a un usuario autenticado comprar los artículos que tiene en su carrito.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Compra realizada con éxito", content = @Content(schema = @Schema(implementation = SaleReportResponse.class))),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content),
            @ApiResponse(responseCode = "403", description = "Prohibido", content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    @PreAuthorize(Util.ROLE_CLIENT )
    @PostMapping("/buy-cart")
    public ResponseEntity<SaleReportResponse> buyItems() {

        return ResponseEntity.ok(saleHandler.buyItemsFromTheCart());
    }
}
