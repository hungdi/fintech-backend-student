package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import java.math.BigDecimal;

/** TODO [특강 1 / 2-1] 계좌별 잔액 행과 원장 금액, 사용가능잔액의 매핑을 작성하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class DmAccountBalance extends BaseTimeEntity {

    private Long accountBalanceId;

    private DmAccount account;

    private BigDecimal ledgerBalance;

    private BigDecimal availableBalance;

    private Long version;

    protected DmAccountBalance() {
    }

    // 2-1의 잔액 생성 과제 전에 StoredMoney의 표현 범위와 0 이상 검증을 구현하세요.
    public DmAccountBalance(DmAccount account, BigDecimal ledgerBalance, BigDecimal availableBalance) {
        this.account = account;
        this.ledgerBalance = StoredMoney.nonNegative(ledgerBalance, "원장 금액");
        this.availableBalance = StoredMoney.nonNegative(availableBalance, "사용가능잔액");
    }

    public void increase(BigDecimal amount) {
        // TODO [특강 2 / 2-3] 입금 금액과 변경 후 두 잔액의 저장 범위를 확인한 뒤 함께 갱신하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-3] 입금 금액과 변경 후 두 잔액의 저장 범위를 확인한 뒤 함께 갱신하세요.");
    }

    public void decrease(BigDecimal amount) {
        // TODO [특강 2 / 2-3] 사용가능잔액과 출금 금액을 비교하고 변경 후 두 잔액의 저장 범위를 검증하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-3] 사용가능잔액과 출금 금액을 비교하고 변경 후 두 잔액의 저장 범위를 검증하세요.");
    }

    public Long getAccountBalanceId() {
        return accountBalanceId;
    }

    public DmAccount getAccount() {
        return account;
    }

    public BigDecimal getLedgerBalance() {
        return ledgerBalance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public Long getVersion() {
        return version;
    }
}
