package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.article_dto.ArticleQuantityRequest;
import com.transaction_microservice.transaction.domain.spi.IStockConnectionPersistencePort;
import com.transaction_microservice.transaction.infrastructure.feign.IStockFeignClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StockConnectionAdapter implements IStockConnectionPersistencePort {

    private final IStockFeignClient stockFeignClient;

    @Override
    public boolean existById(Long articleId) {
        try {
            return stockFeignClient.getArticleById(articleId);
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    @Override
    public boolean isStockSufficient(Long articleId, Integer articleQuantity) {
        try {
            return stockFeignClient.isStockSufficient(articleId, articleQuantity);
        } catch (FeignException.NotFound e) {
            return false;
        }
    }

    @Override
    public void updateQuantityArticle(Long articleId, Integer quantity) {
        ArticleQuantityRequest articleQuantityRequest = new ArticleQuantityRequest(quantity);
        stockFeignClient.updateArticleQuantity(articleId, articleQuantityRequest);
    }

    @Override
    public void reduceArticleQuantity(Long articleId, Integer articleQuantity) {
        ArticleQuantityRequest articleQuantityRequest = new ArticleQuantityRequest(articleQuantity);
        stockFeignClient.reduceArticleQuantity(articleId, articleQuantityRequest);
    }

    @Override
    public Double getArticlePriceById(Long articleId) {
        return stockFeignClient.getArticlePriceById(articleId);
    }


}
