package com.sparta.fintech.ledger.transfer.service;

import java.math.BigDecimal;

public record TransferCommand(
    String idempotencyKey,
    String withdrawalAccountNo,
    String depositAccountNo,
    BigDecimal amount,
    String currencyCode,
    String description
) {
}
