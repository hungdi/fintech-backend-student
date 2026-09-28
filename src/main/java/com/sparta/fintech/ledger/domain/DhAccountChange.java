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
import java.time.Instant;

/** TODO [특강 1 / 3-2] 계좌와 변경 이력의 관계 및 변경 시각을 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class DhAccountChange extends BaseTimeEntity {

    private Long accountChangeId;

    private DmAccount account;

    private AccountChangeType changeType;

    private String beforeValue;

    private String afterValue;

    private String reason;

    private Instant changedAt;

    protected DhAccountChange() {
    }

    public DhAccountChange(
        DmAccount account,
        AccountChangeType changeType,
        String beforeValue,
        String afterValue,
        String reason
    ) {
        this(account, changeType, beforeValue, afterValue, reason, Instant.now());
    }

    public DhAccountChange(
        DmAccount account,
        AccountChangeType changeType,
        String beforeValue,
        String afterValue,
        String reason,
        Instant now
    ) {
        this.account = account;
        this.changeType = changeType;
        this.beforeValue = beforeValue;
        this.afterValue = afterValue;
        this.reason = reason;
        this.changedAt = now;
    }

    public Long getAccountChangeId() {
        return accountChangeId;
    }

    public DmAccount getAccount() {
        return account;
    }

    public AccountChangeType getChangeType() {
        return changeType;
    }

    public String getBeforeValue() {
        return beforeValue;
    }

    public String getAfterValue() {
        return afterValue;
    }

    public String getReason() {
        return reason;
    }

    public Instant getChangedAt() {
        return changedAt;
    }
}
