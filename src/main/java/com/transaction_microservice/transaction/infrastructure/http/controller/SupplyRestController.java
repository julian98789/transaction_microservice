package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.supplydto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supplydto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supplydto.SupplyResponse;
import com.transaction_microservice.transaction.application.handler.supplyhandler.ISupplyHandler;
import com.transaction_microservice.transaction.domain.util.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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


    @Operation(summary = "Add product to supply",
            description = "This endpoint allows adding a product to the supply by specifying " +
                    "the article ID and supply data.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product successfully added to supply",
                    content = @Content(schema = @Schema(implementation = SupplyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Invalid request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PreAuthorize(Util.ROLE_AUX_BODEGA )
    @PostMapping("/agregar-articulo/{articleId}")
    public ResponseEntity<SupplyResponse> agregarArticletToSupply
            (@Valid @PathVariable Long articleId,
             @RequestBody SupplyRequest supplyRequest)
    {

        SupplyResponse supplyResponse = supplyHandler.saveSupply(supplyRequest, articleId);

        return ResponseEntity.status(HttpStatus.CREATED).body(supplyResponse);
    }

    @Operation(summary = "Get the next supply date",
            description = "This endpoint allows obtaining the next supply date by specifying the supply ID.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Next supply date successfully obtained",
                    content = @Content(schema = @Schema(implementation = NextSupplyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Supply not found", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PreAuthorize(Util.ROLE_AUX_BODEGA )
    @GetMapping("/next-supply-date/{supplyId}")
    ResponseEntity<NextSupplyResponse> getNextSupplyDate(@PathVariable Long supplyId) {

        NextSupplyResponse nextSupplyDate = supplyHandler.getNextSupplyDate(supplyId);

        return ResponseEntity.status(HttpStatus.OK).body(nextSupplyDate);
    }


}
