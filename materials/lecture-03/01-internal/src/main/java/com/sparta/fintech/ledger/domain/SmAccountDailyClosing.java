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
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

/**
 * TODO [특강 3 / 2-5-1] 계좌와 날짜의 유일성, 금액의 저장 범위와 불변 필드를 매핑하세요.
 * 필드, 생성자와 getter는 호출 계약입니다. 초기 기준 등록과 검증 상태 변경은 직접 구현합니다.
 */
public class SmAccountDailyClosing extends BaseTimeEntity {

    private Long accountDailyClosingId;
    private DmAccount account;
    private LocalDate baseDate;
    private BigDecimal closingLedgerBalance;
    private DailyClosingStatus status;
    private Instant capturedAt;
    private Instant verifiedAt;
    private SsReconciliationRun verifiedRun;
    private boolean openingBaseline;

    protected SmAccountDailyClosing() {
    }

    public SmAccountDailyClosing(DmAccount account, LocalDate baseDate,
        BigDecimal closingLedgerBalance, Instant capturedAt) {
        this.account = account;
        this.baseDate = baseDate;
        this.closingLedgerBalance = StoredMoney.nonNegative(closingLedgerBalance, "마감 원장 잔액");
        this.capturedAt = capturedAt;
        this.status = DailyClosingStatus.CAPTURED;
    }

    public static SmAccountDailyClosing openingBaseline(DmAccount account, LocalDate baseDate,
        BigDecimal closingLedgerBalance, Instant now) {
        // TODO [특강 3 / 2-5-1] 거래 전에 확인한 기초 잔액임을 표시하고 VERIFIED 기준 기록을 만드세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-5-1] 명시적인 기초 잔액 기록을 만드세요.");
    }

    public void markVerified(SsReconciliationRun run, Instant now) {
        // TODO [특강 3 / 2-5-1] 날짜가 같고 불일치 없이 완료된 실행인지 검사한 뒤 검증 정보를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-5-1] 대사 완료 조건과 검증 상태를 연결하세요.");
    }

    public void markUnverified() {
        // TODO [특강 3 / 2-5-1] 보관한 금액과 시각은 유지하고 검증 상태와 참조만 취소하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-5-1] 마감 검증 상태를 취소하세요.");
    }

    public Long getAccountDailyClosingId() { return accountDailyClosingId; }
    public DmAccount getAccount() { return account; }
    public LocalDate getBaseDate() { return baseDate; }
    public BigDecimal getClosingLedgerBalance() { return closingLedgerBalance; }
    public DailyClosingStatus getStatus() { return status; }
    public Instant getCapturedAt() { return capturedAt; }
    public Instant getVerifiedAt() { return verifiedAt; }
    public SsReconciliationRun getVerifiedRun() { return verifiedRun; }
    public boolean isOpeningBaseline() { return openingBaseline; }
}
