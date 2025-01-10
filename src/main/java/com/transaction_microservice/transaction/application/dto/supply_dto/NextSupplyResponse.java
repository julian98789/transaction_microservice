package com.transaction_microservice.transaction.application.dto.supply_dto;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NextSupplyResponse {
    @Temporal(TemporalType.DATE)
    private String nextSupplyDate;
}
