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
import java.math.BigDecimal;
import java.time.LocalDate;

/** TODO [특강 3 / 4-3-1] 기관, 통화와 기준일 및 내부와 외부 합계 차이를 저장하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class SmSettlement extends BaseTimeEntity {

    private Long settlementId;

    private LocalDate baseDate;

    private String externalInstitutionCode;

    private String currencyCode;

    private BigDecimal internalTotalAmount;

    private BigDecimal externalTotalAmount;

    private BigDecimal settlementAmount;

    private SettlementStatus settlementStatus;

    protected SmSettlement() {
    }

    public SmSettlement(
        LocalDate baseDate,
        String externalInstitutionCode,
        BigDecimal internalTotalAmount,
        BigDecimal externalTotalAmount
    ) {
        this(baseDate, externalInstitutionCode, "KRW", internalTotalAmount, externalTotalAmount);
    }

    public SmSettlement(LocalDate baseDate, String externalInstitutionCode, String currencyCode,
                        BigDecimal internalTotalAmount, BigDecimal externalTotalAmount) {
        // TODO [특강 3 / 4-3-1] 기관, 통화와 기준일 및 내부와 외부 합계 차이를 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-3-1] 기관, 통화와 기준일 및 내부와 외부 합계 차이를 저장하세요.");
    }

    public void confirm() {
        // TODO [특강 3 / 4-3-1] 기관, 통화와 기준일 및 내부와 외부 합계 차이를 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-3-1] 기관, 통화와 기준일 및 내부와 외부 합계 차이를 저장하세요.");
    }

    public Long getSettlementId() {
        return settlementId;
    }

    public LocalDate getBaseDate() {
        return baseDate;
    }

    public String getCurrencyCode() { return currencyCode; }

    public String getExternalInstitutionCode() {
        return externalInstitutionCode;
    }

    public BigDecimal getInternalTotalAmount() {
        return internalTotalAmount;
    }

    public BigDecimal getExternalTotalAmount() {
        return externalTotalAmount;
    }

    public BigDecimal getSettlementAmount() {
        return settlementAmount;
    }

    public SettlementStatus getSettlementStatus() {
        return settlementStatus;
    }
}
