package com.sparta.fintech.ledger.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/** 외부 자료와 내부 대상의 거래별 비교 실행 이력. 합계 차액은 정산 지급액이 아니다. */

/** TODO [특강 3 / 4-3-1] sm_external_reconciliation의 PK·필수 범위·출처·fingerprint 및 DECIMAL(19,2) 합계/차액을 매핑하세요. differenceAmount는 외부−내부이며 순정산액이 아닙니다. 상세 불일치가 하나라도 있으면 MISMATCH를 기록합니다. 필드·단순 getter·Service 주입은 제공합니다. */
public class SmExternalReconciliation extends BaseTimeEntity {

    private Long externalReconciliationId;
    private LocalDate baseDate;
    private String externalInstitutionCode;
    private String currencyCode;
    private ExternalReconciliationSource source;
    private String targetFingerprint;
    private BigDecimal internalTotalAmount;
    private BigDecimal externalTotalAmount;
    private BigDecimal differenceAmount;
    private ReconciliationResultStatus resultStatus;

    protected SmExternalReconciliation() {}
    public SmExternalReconciliation(LocalDate date, String institution, BigDecimal internal, BigDecimal external) {
        // TODO [특강 3 / 4-3-1] 합계·차액·출처와 상세 불일치 상태를 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-3-1] 합계·차액·출처와 상세 불일치 상태를 저장하세요.");
    }
    public SmExternalReconciliation(LocalDate date, String institution, String currency,
                                    ExternalReconciliationSource source, String fingerprint,
                                    BigDecimal internal, BigDecimal external) {
        // TODO [특강 3 / 4-3-1] 합계·차액·출처와 상세 불일치 상태를 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-3-1] 합계·차액·출처와 상세 불일치 상태를 저장하세요.");
    }
    public void markMismatch() {
        // TODO [특강 3 / 4-3-1] 합계·차액·출처와 상세 불일치 상태를 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-3-1] 합계·차액·출처와 상세 불일치 상태를 저장하세요.");
    }
    public Long getExternalReconciliationId() { return externalReconciliationId; }
    public LocalDate getBaseDate() { return baseDate; }
    public String getExternalInstitutionCode() { return externalInstitutionCode; }
    public String getCurrencyCode() { return currencyCode; }
    public ExternalReconciliationSource getSource() { return source; }
    public String getTargetFingerprint() { return targetFingerprint; }
    public BigDecimal getInternalTotalAmount() { return internalTotalAmount; }
    public BigDecimal getExternalTotalAmount() { return externalTotalAmount; }
    public BigDecimal getDifferenceAmount() { return differenceAmount; }
    public ReconciliationResultStatus getResultStatus() { return resultStatus; }
}
