package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

/** TODO [특강 3 / 4-4-2] 누락과 0원 자료를 구분하고 외부 금액과 내부 금액의 차이를 판정하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class SiSettlementDetail extends BaseTimeEntity {

    private Long settlementDetailId;

    private SmSettlement settlement;

    private String targetTid;

    private String targetGid;

    private BigDecimal internalAmount;

    private BigDecimal externalAmount;

    private BigDecimal differenceAmount;

    private boolean matched;

    private String reason;

    protected SiSettlementDetail() {
    }

    public SiSettlementDetail(
        SmSettlement settlement,
        String targetTid,
        String targetGid,
        BigDecimal internalAmount,
        BigDecimal externalAmount,
        String reason
    ) {
        // TODO [특강 3 / 4-4-2] 누락과 0원 자료를 구분하고 외부 금액과 내부 금액의 차이를 판정하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-4-2] 누락과 0원 자료를 구분하고 외부 금액과 내부 금액의 차이를 판정하세요.");
    }

    public Long getSettlementDetailId() {
        return settlementDetailId;
    }

    public SmSettlement getSettlement() {
        return settlement;
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
