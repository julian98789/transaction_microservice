package com.transaction_microservice.transaction.application.dto.sale_dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SaleReportResponse {
    private Long id;
    private Long userId;
    private String userEmail;
    private LocalDate creationDate;
    private Double total;
    private List<SaleDetailsRequest> saleDetails = new ArrayList<>();
}