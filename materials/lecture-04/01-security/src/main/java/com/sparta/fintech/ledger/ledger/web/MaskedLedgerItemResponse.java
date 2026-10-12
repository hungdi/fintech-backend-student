package com.sparta.fintech.ledger.ledger.web;

import com.sparta.fintech.ledger.domain.DebitCreditType;
import com.sparta.fintech.ledger.ledger.service.MaskedLedgerItem;
import java.math.BigDecimal;
import java.time.LocalDate;

public record MaskedLedgerItemResponse(
    Long accountingLedgerItemId,
    String ledgerAccountCode,
    String ledgerAccountName,
    String maskedCustomerAccountNo,
    DebitCreditType debitCreditType,
    BigDecimal amount,
    LocalDate transactionDate
) {

    public static MaskedLedgerItemResponse from(MaskedLedgerItem item) {
        return new MaskedLedgerItemResponse(
            item.accountingLedgerItemId(),
            item.ledgerAccountCode(),
            item.ledgerAccountName(),
            item.maskedCustomerAccountNo(),
            item.debitCreditType(),
            item.amount(),
            item.transactionDate()
        );
    }
}
