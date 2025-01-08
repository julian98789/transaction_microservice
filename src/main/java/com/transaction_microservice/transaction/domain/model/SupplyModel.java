package com.transaction_microservice.transaction.domain.model;

import java.time.LocalDateTime;

public class SupplyModel {

    private Long id;

    private Long articleId;

    private Integer quantity;

    private Long userId;

    private LocalDateTime creationDate;

    private LocalDateTime nextSupplyDate;

    public SupplyModel(Long id, Long articleId, Integer quantity, Long userId,
                       LocalDateTime creationDate, LocalDateTime nextSupplyDate) {
        this.id = id;
        this.articleId = articleId;
        this.quantity = quantity;
        this.userId = userId;
        this.creationDate = creationDate;
        this.nextSupplyDate = nextSupplyDate;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getNextSupplyDate() {
        return nextSupplyDate;
    }

    public void setNextSupplyDate(LocalDateTime nextSupplyDate) {
        this.nextSupplyDate = nextSupplyDate;
    }
}
