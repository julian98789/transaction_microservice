package com.transaction_microservice.transaction.domain.exception;

public class PurchaseException extends RuntimeException {
    public PurchaseException(String message, Throwable cause) {
        super(message, cause);
    }
}