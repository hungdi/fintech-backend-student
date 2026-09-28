package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;

/** TODO [특강 1 / 1-2] 고객과 계좌의 관계, 계좌번호의 유일성 및 상태 변경을 구현하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class DmAccount extends BaseTimeEntity {

    private Long accountId;

    private CmCustomer customer;

    private String accountNo;

    private String accountName;

    private String currencyCode;

    private AccountStatus status;

    private Instant openedAt;

    private Instant closedAt;

    protected DmAccount() {
    }

    public DmAccount(CmCustomer customer, String accountNo, String accountName, String currencyCode) {
        this(customer, accountNo, accountName, currencyCode, Instant.now());
    }

    public DmAccount(CmCustomer customer, String accountNo, String accountName, String currencyCode, Instant now) {
        this.customer = customer;
        this.accountNo = accountNo;
        this.accountName = accountName;
        this.currencyCode = currencyCode;
        this.status = AccountStatus.ACTIVE;
        this.openedAt = now;
    }

    public void suspend() {
        // TODO [특강 1 / 2-2] 계좌를 정지 상태로 바꾸세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 2-2] 계좌를 정지 상태로 바꾸세요.");
    }

    public void close() {
        close(Instant.now());
    }

    public void close(Instant now) {
        // TODO [특강 1 / 2-2] 계좌 종료 상태와 종료 시각을 함께 기록하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 2-2] 계좌 종료 상태와 종료 시각을 함께 기록하세요.");
    }

    public Long getAccountId() {
        return accountId;
    }

    public CmCustomer getCustomer() {
        return customer;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public Instant getOpenedAt() {
        return openedAt;
    }

    public Instant getClosedAt() {
        return closedAt;
    }
}
