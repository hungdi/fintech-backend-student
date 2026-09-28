package com.sparta.fintech.ledger.reconciliation.service;

import java.math.BigDecimal;

public record ExternalSettlementItem(
    String targetTid,
    String targetGid,
    BigDecimal amount
) {
}
