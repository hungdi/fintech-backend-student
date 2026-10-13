package com.sparta.fintech.ledger.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/** 기관 간 약정의 순정산 계산/금액 확인. CONFIRMED도 지급 실행 완료를 뜻하지 않는다. */

/** TODO [특강 3 / 정산-5~정산-7] sm_settlement의 PK·범위 UNIQUE·대사 FK·Version과 금액을 매핑하세요. 순정산액=지급액−수취액, 양수는 순지급/음수는 순수취입니다. CONFIRMED는 담당자 확인이며 실제 지급 완료가 아닙니다. 필드·단순 getter·Service 주입은 제공합니다. */
public class SmSettlement extends BaseTimeEntity {
    private Long settlementId;
    private LocalDate baseDate;
    private String externalInstitutionCode;
    private String currencyCode;
    private BigDecimal payableAmount;
    private BigDecimal receivableAmount;
    /** 양수: 우리 기관의 순지급, 음수: 순수취, 0: 상계 후 지급/수취 없음. */
    private BigDecimal netSettlementAmount;
    private SettlementStatus settlementStatus;

    private SmExternalReconciliation externalReconciliation;
    private String targetFingerprint;
    private long version;

    protected SmSettlement() {}
    public SmSettlement(LocalDate date, String institution, String currency, BigDecimal payable,
                        BigDecimal receivable, SmExternalReconciliation reconciliation, String fingerprint) {
        this.baseDate = date;
        this.externalInstitutionCode = institution;
        this.currencyCode = currency;
        recalculate(payable, receivable, reconciliation, fingerprint);
    }
    public void recalculate(BigDecimal payable, BigDecimal receivable,
                            SmExternalReconciliation reconciliation, String fingerprint) {
        // TODO [특강 3 / 정산-5~정산-7] 지급·수취·순액과 대사 연결, 재계산·확정 상태 규칙을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-5~정산-7] 지급·수취·순액과 대사 연결, 재계산·확정 상태 규칙을 구현하세요.");
    }
    public void confirm() {
        // TODO [특강 3 / 정산-5~정산-7] 지급·수취·순액과 대사 연결, 재계산·확정 상태 규칙을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-5~정산-7] 지급·수취·순액과 대사 연결, 재계산·확정 상태 규칙을 구현하세요.");
    }
    public Long getSettlementId() { return settlementId; }
    public LocalDate getBaseDate() { return baseDate; }
    public String getExternalInstitutionCode() { return externalInstitutionCode; }
    public String getCurrencyCode() { return currencyCode; }
    public BigDecimal getPayableAmount() { return payableAmount; }
    public BigDecimal getReceivableAmount() { return receivableAmount; }
    public BigDecimal getNetSettlementAmount() { return netSettlementAmount; }
    public SettlementStatus getSettlementStatus() { return settlementStatus; }
    public SmExternalReconciliation getExternalReconciliation() { return externalReconciliation; }
    public String getTargetFingerprint() { return targetFingerprint; }
}
