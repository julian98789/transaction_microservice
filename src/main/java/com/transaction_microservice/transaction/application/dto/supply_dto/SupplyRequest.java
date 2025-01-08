package com.transaction_microservice.transaction.application.dto.supply_dto;

import com.transaction_microservice.transaction.domain.util.Util;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SupplyRequest {

    @NotNull( message = Util.ARTICLE_QUANTITY_REQUIRED)
    private Integer articleQuantity;

    @NotNull( message = Util.NEXT_SUPPLY_DATE_NOT_FOUND)
    @Temporal(TemporalType.DATE)
    private LocalDate nextSupplyDate;
}
