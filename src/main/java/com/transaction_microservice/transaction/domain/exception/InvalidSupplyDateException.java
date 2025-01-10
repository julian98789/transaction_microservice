package com.transaction_microservice.transaction.domain.exception;

public class InvalidSupplyDateException extends RuntimeException {

    public InvalidSupplyDateException(String message) {
        super(message);
    }
}
