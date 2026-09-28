package com.sparta.fintech.ledger.account.service;

import com.sparta.fintech.ledger.domain.AccountStatus;
import java.math.BigDecimal;

public record AccountSummary(
    Long accountId,
    String maskedAccountNo,
    String accountName,
    String currencyCode,
    AccountStatus status,
    BigDecimal availableBalance,
    String maskedEmail,
    String maskedPhoneNumber
) {
}
