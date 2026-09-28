package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/** TODO [특강 1 / 4-1] 계정코드의 유일성과 계정과목의 잔액이 보통 남는 쪽인 차변 또는 대변을 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class LcLedgerAccount extends BaseTimeEntity {

    private Long ledgerAccountCodeId;

    private String accountCode;

    private String accountName;

    private DebitCreditType normalBalanceType;

    private boolean active;

    protected LcLedgerAccount() {
    }

    public LcLedgerAccount(String accountCode, String accountName, DebitCreditType normalBalanceType) {
        this.accountCode = accountCode;
        this.accountName = accountName;
        this.normalBalanceType = normalBalanceType;
        this.active = true;
    }

    public Long getLedgerAccountCodeId() {
        return ledgerAccountCodeId;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public DebitCreditType getNormalBalanceType() {
        return normalBalanceType;
    }

    public boolean isActive() {
        return active;
    }
}
