package com.sparta.fintech.ledger.transfer.service;

public class TransferInProgressException extends IllegalStateException {
    public TransferInProgressException(String message) { super(message); }
}
