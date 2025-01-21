package com.transaction_microservice.transaction.infrastructure.http.controller;

import com.transaction_microservice.transaction.application.dto.supply_dto.NextSupplyResponse;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyRequest;
import com.transaction_microservice.transaction.application.dto.supply_dto.SupplyResponse;
import com.transaction_microservice.transaction.application.handler.ISupplyHandler;
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


    @Operation(summary = "Agregar producto al suministro",
            description = "Este endpoint permite agregar un producto al suministro especificando el ID del artículo y los datos del suministro.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Producto agregado al suministro exitosamente",
                    content = @Content(schema = @Schema(implementation = SupplyResponse.class))),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @PreAuthorize(Util.ROLE_AUX_BODEGA )
    @PostMapping("/agregar-articulo/{articleId}")
    public ResponseEntity<SupplyResponse> agregarArticletToSupply(@Valid @PathVariable Long articleId, @RequestBody SupplyRequest supplyRequest) {

        SupplyResponse supplyResponse = supplyHandler.saveSupply(supplyRequest, articleId);

        return ResponseEntity.status(HttpStatus.CREATED).body(supplyResponse);
    }

    @Operation(summary = "Obtener la próxima fecha de suministro",
            description = "Este endpoint permite obtener la próxima fecha de suministro especificando el ID del suministro.",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Próxima fecha de suministro obtenida exitosamente",
                    content = @Content(schema = @Schema(implementation = NextSupplyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Suministro no encontrado", content = @Content),
            @ApiResponse(responseCode = "401", description = "No autorizado", content = @Content)
    })
    @PreAuthorize(Util.ROLE_AUX_BODEGA )
    @GetMapping("/next-supply-date/{supplyId}")
    ResponseEntity<NextSupplyResponse> getNextSupplyDate(@PathVariable Long supplyId) {

        NextSupplyResponse nextSupplyDate = supplyHandler.getNextSupplyDate(supplyId);

        return ResponseEntity.status(HttpStatus.OK).body(nextSupplyDate);
    }


}
