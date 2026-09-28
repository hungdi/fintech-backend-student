package com.sparta.fintech.ledger.transfer.service;

import org.springframework.stereotype.Component;

@Component
public class NoOpTransferFailureHook implements TransferFailureHook {

    @Override
    public void throwIfRequested(TransferFailurePoint failurePoint) {

    }
}
