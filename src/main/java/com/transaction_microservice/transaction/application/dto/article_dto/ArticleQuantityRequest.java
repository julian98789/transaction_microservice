package com.transaction_microservice.transaction.application.dto.article_dto;

import com.transaction_microservice.transaction.domain.util.Util;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleQuantityRequest {

    @NotNull( message = Util.ARTICLE_QUANTITY_REQUIRED)
    private Integer articleQuantity;
}
