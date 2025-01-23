package com.transaction_microservice.transaction.application.dto.sale_dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class SaleRequest {

    private Long id;
    private Long userId;
    private Double total;
    private LocalDate creationDate;
    private List<SaleDetailsRequest> saleDetails;

}
