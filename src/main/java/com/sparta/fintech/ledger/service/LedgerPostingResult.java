package com.sparta.fintech.ledger.service;

import java.math.BigDecimal;

public record LedgerPostingResult(
    Long transactionId,
    Long journalEntryId,
    String tid,
    String voucherNo,
    BigDecimal debitTotal,
    BigDecimal creditTotal
) {
}
