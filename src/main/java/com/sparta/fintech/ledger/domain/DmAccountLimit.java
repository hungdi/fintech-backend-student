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
import java.math.BigDecimal;
import java.time.LocalDate;

/** TODO [특강 1 / 2-3] 고객과 계좌의 한도 관계, 기간과 유효일 필드를 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class DmAccountLimit extends BaseTimeEntity {

    private Long accountLimitId;

    private CmCustomer customer;

    private DmAccount account;

    private LimitType limitType;

    private LimitPeriod limitPeriod;

    private BigDecimal limitAmount;

    private String currencyCode;

    private LocalDate effectiveFrom;

    private LocalDate effectiveTo;

    private boolean active;

    protected DmAccountLimit() {
    }

    public DmAccountLimit(
        CmCustomer customer,
        DmAccount account,
        LimitType limitType,
        LimitPeriod limitPeriod,
        BigDecimal limitAmount,
        String currencyCode,
        LocalDate effectiveFrom
    ) {
        this.customer = customer;
        this.account = account;
        this.limitType = limitType;
        this.limitPeriod = limitPeriod;
        this.limitAmount = limitAmount;
        this.currencyCode = currencyCode;
        this.effectiveFrom = effectiveFrom;
        this.active = true;
    }

    public Long getAccountLimitId() {
        return accountLimitId;
    }

    public CmCustomer getCustomer() {
        return customer;
    }

    public DmAccount getAccount() {
        return account;
    }

    public LimitType getLimitType() {
        return limitType;
    }

    public LimitPeriod getLimitPeriod() {
        return limitPeriod;
    }

    public BigDecimal getLimitAmount() {
        return limitAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }

    public boolean isActive() {
        return active;
    }
}
