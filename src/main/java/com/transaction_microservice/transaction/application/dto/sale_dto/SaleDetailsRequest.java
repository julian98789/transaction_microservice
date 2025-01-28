package com.transaction_microservice.transaction.application.dto.sale_dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SaleDetailsRequest {

    private Long id;

    private Long articleId;

    private Integer quantity;

    private Double price;

    private Double subtotal;
}
