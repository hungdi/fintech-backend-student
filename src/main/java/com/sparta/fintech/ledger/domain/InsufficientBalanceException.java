package com.sparta.fintech.ledger.domain;

public class InsufficientBalanceException extends IllegalArgumentException {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}
