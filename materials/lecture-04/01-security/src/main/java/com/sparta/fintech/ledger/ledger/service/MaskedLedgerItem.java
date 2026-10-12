package com.sparta.fintech.ledger.ledger.service;

import com.sparta.fintech.ledger.domain.DebitCreditType;
import java.math.BigDecimal;
import java.time.LocalDate;

public record MaskedLedgerItem(
    Long accountingLedgerItemId,
    String ledgerAccountCode,
    String ledgerAccountName,
    String maskedCustomerAccountNo,
    DebitCreditType debitCreditType,
    BigDecimal amount,
    LocalDate transactionDate
) {
}
