package com.sparta.fintech.ledger.domain;

/** 특강 #3에서 비교하는 세 가지 원장 관계. */
public enum ReconciliationTargetType {
    /** 검증된 전일 마감과 대상일 입출금을 대상일 원장 마감과 비교. */
    ACCOUNT_BALANCE_VS_ACCOUNT_TRANSACTION,

    /** 완료 거래와 연결된 입출금 내역의 건수, 방향, 계좌와 금액 비교. */
    TRANSACTION_VS_ACCOUNT_TRANSACTION,

    /** 계좌원장과 거래 ID로 연결된 회계원장의 개별 내역 비교. */
    ACCOUNT_TRANSACTION_VS_ACCOUNTING_LEDGER
}
