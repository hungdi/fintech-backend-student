package com.sparta.fintech.ledger.transfer.service;

import com.sparta.fintech.ledger.domain.TransferOrderStatus;
import java.math.BigDecimal;

public record TransferResult(
    Long transferOrderId,
    String tid,
    String gid,
    String journalTid,
    String accountingLedgerTid,
    String voucherNo,
    TransferOrderStatus status,
    BigDecimal withdrawalAccountBalance,
    BigDecimal depositAccountBalance
) {
}
