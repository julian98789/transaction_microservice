package com.transaction_microservice.transaction.domain.exception;

public class CartEmptyException extends RuntimeException {

    public CartEmptyException(String message) {
        super(message);
    }

}
