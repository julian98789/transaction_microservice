package com.transaction_microservice.transaction.domain.model;

import java.time.LocalDate;
import java.util.List;

public class SaleReportModel {

    private Long id;
    private Long userId;
    private String userEmail;
    private LocalDate creationDate;
    private Double total;
    private List<SaleDetailsModel> saleDetails;

    public SaleReportModel(Long id, Long userId, String userEmail, LocalDate creationDate, Double total, List<SaleDetailsModel> saleDetails) {
        this.id = id;
        this.userId = userId;
        this.userEmail = userEmail;
        this.creationDate = creationDate;
        this.total = total;
        this.saleDetails = saleDetails;
    }

    public SaleReportModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<SaleDetailsModel> getSaleDetails() {
        return saleDetails;
    }

    public void setSaleDetails(List<SaleDetailsModel> saleDetails) {
        this.saleDetails = saleDetails;
    }
}