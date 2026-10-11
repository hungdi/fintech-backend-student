package com.sparta.fintech.ledger.domain;

import java.math.BigDecimal;

/** TODO [특강 3 / 4-4-2] 대사 실행 FK와 TID/GID, DECIMAL(19,2) 금액·차액·일치 여부·사유를 매핑하세요. null은 누락, 0은 존재하는 거래입니다. 표본 대사는 금액과 지급 방향을 함께 검사합니다. 필드·단순 getter·Service 주입은 제공합니다. */
public class SiExternalReconciliationDetail extends BaseTimeEntity {

    private Long externalReconciliationDetailId;

    private SmExternalReconciliation externalReconciliation;

    private String targetTid;

    private String targetGid;

    private BigDecimal internalAmount;

    private BigDecimal externalAmount;

    private BigDecimal differenceAmount;

    private boolean matched;

    private String reason;

    private SettlementDirection internalDirection;

    private SettlementDirection externalDirection;

    protected SiExternalReconciliationDetail() {
    }

    public SiExternalReconciliationDetail(
        SmExternalReconciliation externalReconciliation,
        String targetTid,
        String targetGid,
        BigDecimal internalAmount,
        BigDecimal externalAmount,
        String reason
    ) {
        // TODO [특강 3 / 4-4-2] 누락과0원, 금액 차이, 표본의 지급 방향을 구분해 판정하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-4-2] 누락과0원, 금액 차이, 표본의 지급 방향을 구분해 판정하세요.");
    }

    public SiExternalReconciliationDetail(SmExternalReconciliation run, String tid, String gid,
                                          BigDecimal internal, BigDecimal external, String reason,
                                          SettlementDirection internalDirection, SettlementDirection externalDirection) {
        // TODO [특강 3 / 4-4-2] 누락과0원, 금액 차이, 표본의 지급 방향을 구분해 판정하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-4-2] 누락과0원, 금액 차이, 표본의 지급 방향을 구분해 판정하세요.");
    }
    public SettlementDirection getInternalDirection() { return internalDirection; }
    public SettlementDirection getExternalDirection() { return externalDirection; }

    public Long getExternalReconciliationDetailId() {
        return externalReconciliationDetailId;
    }

    public SmExternalReconciliation getExternalReconciliation() {
        return externalReconciliation;
    }

    public String getTargetTid() {
        return targetTid;
    }

    public String getTargetGid() {
        return targetGid;
    }

    public BigDecimal getInternalAmount() {
        return internalAmount;
    }

    public BigDecimal getExternalAmount() {
        return externalAmount;
    }

    public BigDecimal getDifferenceAmount() {
        return differenceAmount;
    }

    public boolean isMatched() {
        return matched;
    }

    public String getReason() {
        return reason;
    }
}
