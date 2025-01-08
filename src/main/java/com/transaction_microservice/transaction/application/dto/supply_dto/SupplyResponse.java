package com.transaction_microservice.transaction.application.dto.supply_dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SupplyResponse {
    private Long articleId;
    private Integer articleQuantity;
    private LocalDate nextSupplyDate;
}
