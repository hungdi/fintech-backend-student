package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/** TODO [특강 3 / 2-4] 기대값과 실제값의 차이 및 구조 검사 결과로 대사 상태를 정하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class SiReconciliationResult extends BaseTimeEntity {

    private Long reconciliationResultId;

    private SsReconciliationRun reconciliationRun;

    private ReconciliationTargetType targetType;

    private LocalDate baseDate;

    private String targetTid;

    private String targetGid;

    private Long accountId;

    private BigDecimal expectedAmount;

    private BigDecimal actualAmount;

    private BigDecimal differenceAmount;

    private ReconciliationResultStatus resultStatus;

    private String reason;

    protected SiReconciliationResult() {
    }

    public SiReconciliationResult(
        SsReconciliationRun reconciliationRun,
        ReconciliationTargetType targetType,
        LocalDate baseDate,
        String targetTid,
        String targetGid,
        Long accountId,
        BigDecimal expectedAmount,
        BigDecimal actualAmount,
        String reason
    ) {
        this(reconciliationRun, targetType, baseDate, targetTid, targetGid, accountId,
            expectedAmount, actualAmount, true, reason);
    }

    public SiReconciliationResult(
        SsReconciliationRun reconciliationRun,
        ReconciliationTargetType targetType,
        LocalDate baseDate,
        String targetTid,
        String targetGid,
        Long accountId,
        BigDecimal expectedAmount,
        BigDecimal actualAmount,
        boolean structureMatches,
        String reason
    ) {
        // TODO [특강 3 / 2-4] 기대값과 실제값의 차이 및 구조 검사 결과로 대사 상태를 정하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-4] 기대값과 실제값의 차이 및 구조 검사 결과로 대사 상태를 정하세요.");
    }

    public Long getReconciliationResultId() {
        return reconciliationResultId;
    }

    public SsReconciliationRun getReconciliationRun() {
        return reconciliationRun;
    }

    public ReconciliationTargetType getTargetType() {
        return targetType;
    }

    public LocalDate getBaseDate() {
        return baseDate;
    }

    public String getTargetTid() {
        return targetTid;
    }

    public String getTargetGid() {
        return targetGid;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getExpectedAmount() {
        return expectedAmount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public BigDecimal getDifferenceAmount() {
        return differenceAmount;
    }

    public ReconciliationResultStatus getResultStatus() {
        return resultStatus;
    }

    public String getReason() {
        return reason;
    }

    private BigDecimal normalize(BigDecimal amount) {
        // TODO [특강 3 / 2-4] 기대값과 실제값의 차이 및 구조 검사 결과로 대사 상태를 정하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-4] 기대값과 실제값의 차이 및 구조 검사 결과로 대사 상태를 정하세요.");
    }
}
