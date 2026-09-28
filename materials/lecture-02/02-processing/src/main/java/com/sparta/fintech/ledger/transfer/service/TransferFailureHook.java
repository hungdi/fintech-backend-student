package com.sparta.fintech.ledger.transfer.service;

public interface TransferFailureHook {

    void throwIfRequested(TransferFailurePoint failurePoint);
}
