package com.transaction_microservice.transaction.domain.model;


public class SaleDetailsModel {

    private Long id;

    private SalesModel sale;

    private Long articleId;

    private Integer quantity;

    private Double price;

    private Double subtotal;

    public SaleDetailsModel() {
    }

    public SaleDetailsModel(Long id, SalesModel sale, Long articleId, Integer quantity, Double price, Double subtotal) {
        this.id = id;
        this.sale = sale;
        this.articleId = articleId;
        this.quantity = quantity;
        this.price = price;
        this.subtotal = subtotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SalesModel getSale() {
        return sale;
    }

    public void setSale(SalesModel sale) {
        this.sale = sale;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
}
