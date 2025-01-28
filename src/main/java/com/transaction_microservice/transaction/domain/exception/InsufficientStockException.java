package com.transaction_microservice.transaction.domain.exception;

public class InsufficientStockException extends RuntimeException {
    private final String nextSupplyDate;

    public InsufficientStockException(String message, String nextSupplyDate) {
        super(message);
        this.nextSupplyDate = nextSupplyDate;
    }

    public String getNextSupplyDate() {
        return nextSupplyDate;
    }
}