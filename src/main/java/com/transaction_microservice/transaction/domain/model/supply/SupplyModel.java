package com.transaction_microservice.transaction.domain.model.supply;

import java.time.LocalDate;


public class SupplyModel {

    private Long id;

    private Long articleId;

    private Integer quantity;

    private Long userId;

    private LocalDate creationDate;

    private LocalDate nextSupplyDate;

    public SupplyModel(Long id, Long articleId, Integer quantity, Long userId,
                       LocalDate creationDate, LocalDate nextSupplyDate) {
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getNextSupplyDate() {
        return nextSupplyDate;
    }

    public void setNextSupplyDate(LocalDate nextSupplyDate) {
        this.nextSupplyDate = nextSupplyDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}