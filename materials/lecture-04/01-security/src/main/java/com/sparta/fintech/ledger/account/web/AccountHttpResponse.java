package com.sparta.fintech.ledger.account.web;

import com.sparta.fintech.ledger.account.service.AccountSummary;
import com.sparta.fintech.ledger.domain.AccountStatus;
import java.math.BigDecimal;

public record AccountHttpResponse(
    Long accountId,
    String maskedAccountNo,
    String accountName,
    String currencyCode,
    AccountStatus status,
    BigDecimal availableBalance,
    String maskedEmail,
    String maskedPhoneNumber
) {

    public static AccountHttpResponse from(AccountSummary summary) {
        return new AccountHttpResponse(
            summary.accountId(),
            summary.maskedAccountNo(),
            summary.accountName(),
            summary.currencyCode(),
            summary.status(),
            summary.availableBalance(),
            summary.maskedEmail(),
            summary.maskedPhoneNumber()
        );
    }
}
