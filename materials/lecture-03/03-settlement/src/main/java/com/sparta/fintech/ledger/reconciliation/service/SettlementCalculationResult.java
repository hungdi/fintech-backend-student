package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.SettlementStatus;
import java.math.BigDecimal;
import java.time.LocalDate;

public record SettlementCalculationResult(Long settlementId, LocalDate baseDate, String externalInstitutionCode,
    String currencyCode, BigDecimal payableAmount, BigDecimal receivableAmount, BigDecimal netSettlementAmount,
    SettlementStatus settlementStatus, Long externalReconciliationId) {}
