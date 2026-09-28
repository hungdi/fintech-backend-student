package com.sparta.fintech.ledger.reconciliation.service;

import java.time.LocalDate;

public record ReconciliationRunResult(
    Long reconciliationRunId,
    LocalDate baseDate,
    int totalCount,
    int mismatchCount
) {
}
