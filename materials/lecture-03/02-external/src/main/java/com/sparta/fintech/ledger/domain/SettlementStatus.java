package com.sparta.fintech.ledger.domain;

/** 지급 실행 상태가 아니라 기관 간 약정 금액의 계산·담당자 확인 상태이다. */
public enum SettlementStatus {
    /** 지급액·수취액과 순정산 금액을 계산한 상태. */
    CALCULATED,
    /** 거래별 정상 외부 대사를 확인하고 금액을 확정한 상태. 자금 지급은 실행하지 않는다. */
    CONFIRMED
}
