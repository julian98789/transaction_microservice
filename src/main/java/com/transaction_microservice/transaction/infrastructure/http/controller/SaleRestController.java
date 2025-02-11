package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.saledto.SaleReportResponse;
import com.transaction_microservice.transaction.application.handler.salehandler.ISaleHandler;
import com.transaction_microservice.transaction.domain.util.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/supply")
@RequiredArgsConstructor
public class SaleRestController {

    private final ISaleHandler saleHandler;

    @Operation(summary = "Buy items from the cart", description = "This endpoint allows an authenticated user" +
            " to buy the items in their cart.", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Purchase successful",
                    content = @Content(schema = @Schema(implementation = SaleReportResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PreAuthorize(Util.ROLE_CLIENT )
    @PostMapping("/buy-cart")
    public ResponseEntity<SaleReportResponse> buyItems() {

        SaleReportResponse saleReportResponse = saleHandler.buyItemsFromTheCart();

        return ResponseEntity.status(HttpStatus.OK).body(saleReportResponse);
    }
}
