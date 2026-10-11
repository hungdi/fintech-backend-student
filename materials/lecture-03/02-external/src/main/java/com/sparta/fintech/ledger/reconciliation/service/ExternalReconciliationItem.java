package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.SettlementDirection;
import java.math.BigDecimal;

/** 외부 자료의 방향 역시 우리 기관 기준으로 정규화한다. 기존 송금 대사는 금액만 비교한다. */
public record ExternalReconciliationItem(String targetTid, String targetGid, BigDecimal amount,
                                         SettlementDirection paymentDirection) {
    public ExternalReconciliationItem(String targetTid, String targetGid, BigDecimal amount) {
        this(targetTid, targetGid, amount, null);
    }
}
