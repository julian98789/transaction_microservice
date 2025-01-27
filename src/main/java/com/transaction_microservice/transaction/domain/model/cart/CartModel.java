package com.transaction_microservice.transaction.domain.model.cart;

import java.time.LocalDate;

public class CartModel {

    private Long id;

    private Long articleId;

    private Long userId;

    private Integer quantity;

    private LocalDate creationDate;

    private LocalDate lastUpdatedDate;

    public CartModel(Long id, Long articleId, Long userId, Integer quantity, LocalDate creationDate, LocalDate lastUpdatedDate) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.quantity = quantity;
        this.creationDate = creationDate;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDate getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(LocalDate lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}

