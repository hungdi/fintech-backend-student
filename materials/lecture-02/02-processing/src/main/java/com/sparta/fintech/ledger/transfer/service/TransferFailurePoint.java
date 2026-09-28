package com.sparta.fintech.ledger.transfer.service;

public enum TransferFailurePoint {

    AFTER_WITHDRAWAL,

    AFTER_DEPOSIT,

    BEFORE_LEDGER_POSTING,

    AFTER_LEDGER_POSTING
}
