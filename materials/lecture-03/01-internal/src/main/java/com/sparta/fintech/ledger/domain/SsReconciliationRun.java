package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.Instant;

/** TODO [특강 3 / 2-3] 실행 상태와 결과 건수, 완료 시각을 연결하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class SsReconciliationRun extends BaseTimeEntity {

    private Long reconciliationRunId;

    private LocalDate baseDate;

    private ReconciliationRunStatus runStatus;

    private Instant startedAt;

    private Instant completedAt;

    private int totalCount;

    private int mismatchCount;

    private String memo;

    protected SsReconciliationRun() {
    }

    public SsReconciliationRun(LocalDate baseDate, String memo) {
        this(baseDate, memo, Instant.now());
    }

    public SsReconciliationRun(LocalDate baseDate, String memo, Instant now) {
        this.baseDate = baseDate;
        this.memo = memo;
        this.runStatus = ReconciliationRunStatus.RUNNING;
        this.startedAt = now;
    }

    public void complete(int totalCount, int mismatchCount) {
        complete(totalCount, mismatchCount, Instant.now());
    }

    public void complete(int totalCount, int mismatchCount, Instant now) {
        // TODO [특강 3 / 2-3] 실행 상태와 결과 건수, 완료 시각을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-3] 실행 상태와 결과 건수, 완료 시각을 연결하세요.");
    }

    public Long getReconciliationRunId() {
        return reconciliationRunId;
    }

    public LocalDate getBaseDate() {
        return baseDate;
    }

    public ReconciliationRunStatus getRunStatus() {
        return runStatus;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getMismatchCount() {
        return mismatchCount;
    }

    public String getMemo() {
        return memo;
    }
}
