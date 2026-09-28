package com.sparta.fintech.ledger.reconciliation.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SettlementCalculationResult(
    Long settlementId,
    LocalDate baseDate,
    String externalInstitutionCode,
    String currencyCode,
    BigDecimal internalTotalAmount,
    BigDecimal externalTotalAmount,
    BigDecimal settlementAmount,
    int detailCount,
    int mismatchCount
) {
}
