package com.transaction_microservice.transaction.domain.spi;

public interface IStockConnectionPersistencePort {

    boolean existById(Long articleId);

    boolean isStockSufficient(Long articleId, Integer articleQuantity);

    void updateQuantityArticle(Long articleId, Integer articleQuantity);

    void reduceArticleQuantity(Long articleId, Integer articleQuantity);

    Double getArticlePriceById(Long articleId);

}
