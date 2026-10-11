package com.sparta.fintech.ledger.reconciliation.service;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExternalReconciliationResult(Long externalReconciliationId, LocalDate baseDate,
    String externalInstitutionCode, String currencyCode, BigDecimal internalTotalAmount,
    BigDecimal externalTotalAmount, BigDecimal differenceAmount, int detailCount, int mismatchCount) {}
