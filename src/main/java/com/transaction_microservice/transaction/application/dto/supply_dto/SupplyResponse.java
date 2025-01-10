package com.transaction_microservice.transaction.application.dto.supply_dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SupplyResponse {
    private Long id;
    private Long articleId;
    private Integer quantity;
    private Long userId;
    private LocalDate creationDate;
    private LocalDate nextSupplyDate;
}
