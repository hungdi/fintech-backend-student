package com.sparta.fintech.ledger.domain;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public final class LedgerPostingRules {
    public static final String CASH = "100101";
    public static final String CUSTOMER_DEPOSIT = "210101";
    private LedgerPostingRules() {}

    public record AccountItem(Long accountId, DebitCreditType direction, BigDecimal amount) {}
    public record JournalItem(String accountCode, Long customerAccountId, DebitCreditType direction, BigDecimal amount) {}

    public static boolean supports(TransactionType type) {
        // TODO [특강 1 / 4-2] 기본 실습이 지원하는 거래 유형을 구분하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 기본 실습이 지원하는 거래 유형을 구분하세요.");
    }

    public static boolean matches(TransactionType type, BigDecimal amount, List<AccountItem> accounts, List<JournalItem> journals) {
        // TODO [특강 1 / 4-2] 지원 거래 유형별 요청 금액, 차대 방향, 계정코드 및 고객 계좌를 검증하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 지원 거래 유형별 요청 금액, 차대 방향, 계정코드 및 고객 계좌를 검증하세요.");
    }

    private static boolean has(List<JournalItem> journals, String code, Long customer, DebitCreditType direction) {
        // TODO [특강 1 / 4-2] 계정코드, 고객 계좌와 차대 방향이 일치하는 분개 행을 확인하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 계정코드, 고객 계좌와 차대 방향이 일치하는 분개 행을 확인하세요.");
    }
}
