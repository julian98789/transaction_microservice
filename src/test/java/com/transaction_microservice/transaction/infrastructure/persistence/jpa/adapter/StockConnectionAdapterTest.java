package com.transaction_microservice.transaction.infrastructure.persistence.jpa.adapter;

import com.transaction_microservice.transaction.application.dto.article_dto.ArticleQuantityRequest;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StockConnectionAdapterTest {

    @Mock
    private IStockFeignClient stockFeignClient;

    @InjectMocks
    private StockConnectionAdapter stockConnectionAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Existe por ID")
    void testExistById() {
        Long articleId = 1L;
        when(stockFeignClient.getArticleById(articleId)).thenReturn(true);

        boolean result = stockConnectionAdapter.existById(articleId);

        assertTrue(result);
    }

    @Test
    @DisplayName("No existe por ID")
    void testExistById_NotFound() {
        Long articleId = 1L;
        when(stockFeignClient.getArticleById(articleId)).thenThrow(FeignException.NotFound.class);

        boolean result = stockConnectionAdapter.existById(articleId);

        assertFalse(result);
    }

    @Test
    @DisplayName("El stock es suficiente")
    void testIsStockSufficient() {
        Long articleId = 1L;
        Integer articleQuantity = 10;
        when(stockFeignClient.isStockSufficient(articleId, articleQuantity)).thenReturn(true);

        boolean result = stockConnectionAdapter.isStockSufficient(articleId, articleQuantity);

        assertTrue(result);
    }

    @Test
    @DisplayName("El stock no es suficiente")
    void testIsStockSufficient_NotFound() {
        Long articleId = 1L;
        Integer articleQuantity = 10;
        when(stockFeignClient.isStockSufficient(articleId, articleQuantity)).thenThrow(FeignException.NotFound.class);

        boolean result = stockConnectionAdapter.isStockSufficient(articleId, articleQuantity);

        assertFalse(result);
    }

    @Test
    @DisplayName("Actualiza la cantidad del artículo")
    void testUpdateQuantityArticle() {
        Long articleId = 1L;
        Integer quantity = 10;

        stockConnectionAdapter.updateQuantityArticle(articleId, quantity);

        verify(stockFeignClient).updateArticleQuantity(eq(articleId), any(ArticleQuantityRequest.class));
    }

    @Test
    @DisplayName("Obtiene el precio del artículo por ID")
    void testReduceArticleQuantity() {
        Long articleId = 1L;
        Integer articleQuantity = 10;


        stockConnectionAdapter.reduceArticleQuantity(articleId, articleQuantity);

        verify(stockFeignClient).reduceArticleQuantity(eq(articleId), any(ArticleQuantityRequest.class));
    }

    @Test
    @DisplayName("Obtiene el precio del artículo por ID")
    void testGetArticlePriceById() {
        Long articleId = 1L;
        Double price = 100.0;
        when(stockFeignClient.getArticlePriceById(articleId)).thenReturn(price);

        Double result = stockConnectionAdapter.getArticlePriceById(articleId);

        assertEquals(price, result);
    }
}