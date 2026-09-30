package com.sparta.fintech.ledger.domain;

/** CAPTURED는 원장 잔액 보관, VERIFIED는 다음 날 기준으로 사용할 수 있는 상태입니다. */
public enum DailyClosingStatus {
    CAPTURED,
    VERIFIED
}
