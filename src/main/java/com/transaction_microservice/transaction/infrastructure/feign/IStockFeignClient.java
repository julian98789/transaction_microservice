package com.transaction_microservice.transaction.infrastructure.feign;

import com.transaction_microservice.transaction.application.dto.article_dto.ArticleQuantityRequest;
import com.transaction_microservice.transaction.domain.util.Util;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = Util.STOCK_SERVICE_NAME, url = Util.STOCK_SERVICE_URL)
public interface IStockFeignClient {

    @GetMapping("/api/article/{articleId}")
    boolean getArticleById(@PathVariable Long articleId); //enp 4

    @PatchMapping(value = "/api/article/quantity/{articleId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateArticleQuantity(@PathVariable Long articleId, @RequestBody ArticleQuantityRequest articleQuantityRequest);

    @GetMapping("/api/article/{articleId}/check-quantity/{quantity}")//enp 5
    boolean isStockSufficient(@PathVariable Long articleId, @PathVariable Integer quantity);

    @PatchMapping("/api/article/{articleId}/subtract-stock")
    void reduceArticleQuantity(@PathVariable Long articleId, @RequestBody ArticleQuantityRequest articleQuantityRequest);

    @GetMapping("/api/article/{articleId}/price")
    Double getArticlePriceById(@PathVariable Long articleId);
}
