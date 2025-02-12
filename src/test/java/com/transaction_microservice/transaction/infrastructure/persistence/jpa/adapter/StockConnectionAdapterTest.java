package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.articledto.ArticleQuantityRequest;
import com.transaction_microservice.transaction.infrastructure.http.feign.IStockFeignClient;
import feign.FeignException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class StockConnectionAdapterTest {

    @Mock
    private IStockFeignClient stockFeignClient;

    @InjectMocks
    private StockConnectionAdapter stockConnectionAdapter;

    private Long articleId = 1L;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    @DisplayName("Check if article exists by ID - should return true if exists")
    void shouldReturnTrueIfArticleExistsById() {
        articleId = 1L;
        when(stockFeignClient.getArticleById(articleId)).thenReturn(true);

        boolean result = stockConnectionAdapter.existById(articleId);

        assertTrue(result);

        verify(stockFeignClient, times(1)).getArticleById(articleId);
    }

    @Test
    @DisplayName("Check if article exists by ID - should return false if not found")
    void shouldReturnFalseIfArticleDoesNotExistById() {
        articleId = 1L;
        when(stockFeignClient.getArticleById(articleId)).thenThrow(FeignException.NotFound.class);

        boolean result = stockConnectionAdapter.existById(articleId);

        assertFalse(result);

        verify(stockFeignClient, times(1)).getArticleById(articleId);
    }

    @Test
    @DisplayName("Check if stock is sufficient - should return true if sufficient")
    void shouldReturnTrueIfStockIsSufficient() {
        articleId = 1L;
        Integer articleQuantity = 10;
        when(stockFeignClient.isStockSufficient(articleId, articleQuantity)).thenReturn(true);

        boolean result = stockConnectionAdapter.isStockSufficient(articleId, articleQuantity);

        assertTrue(result);

        verify(stockFeignClient, times(1)).isStockSufficient(articleId, articleQuantity);
    }

    @Test
    @DisplayName("Check if stock is sufficient - should return false if not sufficient")
    void shouldReturnFalseIfStockIsNotSufficient() {
        articleId = 1L;
        Integer articleQuantity = 10;
        when(stockFeignClient.isStockSufficient(articleId, articleQuantity)).thenThrow(FeignException.NotFound.class);

        boolean result = stockConnectionAdapter.isStockSufficient(articleId, articleQuantity);

        assertFalse(result);

        verify(stockFeignClient, times(1)).isStockSufficient(articleId, articleQuantity);
    }

    @Test
    @DisplayName("Update article quantity - should update the quantity")
    void shouldUpdateArticleQuantity() {
        articleId = 1L;
        Integer quantity = 10;

        doNothing().when(stockFeignClient).updateArticleQuantity(articleId, new ArticleQuantityRequest(quantity));

        stockConnectionAdapter.updateQuantityArticle(articleId, quantity);

        verify(stockFeignClient).updateArticleQuantity(eq(articleId), any(ArticleQuantityRequest.class));
    }

    @Test
    @DisplayName("Reduce article quantity - should reduce the quantity")
    void shouldReduceArticleQuantity() {
        articleId = 1L;
        Integer articleQuantity = 10;

        doNothing().when(stockFeignClient).reduceArticleQuantity(articleId, new ArticleQuantityRequest(articleQuantity));

        stockConnectionAdapter.reduceArticleQuantity(articleId, articleQuantity);

        verify(stockFeignClient).reduceArticleQuantity(eq(articleId), any(ArticleQuantityRequest.class));
    }

    @Test
    @DisplayName("Get article price by ID - should return the price")
    void shouldReturnArticlePriceById() {
        articleId = 1L;
        Double price = 100.0;
        when(stockFeignClient.getArticlePriceById(articleId)).thenReturn(price);

        Double result = stockConnectionAdapter.getArticlePriceById(articleId);

        assertEquals(price, result);

        verify(stockFeignClient, times(1)).getArticlePriceById(articleId);
    }
}