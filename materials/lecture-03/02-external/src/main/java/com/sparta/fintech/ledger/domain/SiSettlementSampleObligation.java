package com.sparta.fintech.ledger.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

/** 교육용 기관 간 지급·수취 약정 자료. 송금 API의 실행이나 완료를 기록하지 않는다. */

/** TODO [특강 3 / 정산-4] 교육용 기관 간 약정의 PK·필수값·양수 금액·불변 필드와 (기준일,기관,통화,TID) UNIQUE를 매핑하세요. 이 표본은 송금 API 완료나 실제 지급 실행 기록이 아닙니다. 필드·단순 getter·Service 주입은 제공합니다. */
public class SiSettlementSampleObligation extends BaseTimeEntity {

    private Long settlementSampleObligationId;

    private LocalDate baseDate;

    private String externalInstitutionCode;

    private String currencyCode;

    private String targetTid;

    private String targetGid;

    private SettlementDirection paymentDirection;

    private BigDecimal amount;

    protected SiSettlementSampleObligation() {}
    public SiSettlementSampleObligation(LocalDate baseDate, String externalInstitutionCode, String currencyCode,
                                       String targetTid, String targetGid, SettlementDirection paymentDirection, BigDecimal amount) {
        // TODO [특강 3 / 정산-4] 필수 범위·TID/GID·지급 방향과 양수 금액을 검증하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-4] 필수 범위·TID/GID·지급 방향과 양수 금액을 검증하세요.");
    }
    private static void requireText(String value, int max, String name) {
        // TODO [특강 3 / 정산-4] 필수 범위·TID/GID·지급 방향과 양수 금액을 검증하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 정산-4] 필수 범위·TID/GID·지급 방향과 양수 금액을 검증하세요.");
    }
    public Long getSettlementSampleObligationId() { return settlementSampleObligationId; }
    public LocalDate getBaseDate() { return baseDate; }
    public String getExternalInstitutionCode() { return externalInstitutionCode; }
    public String getCurrencyCode() { return currencyCode; }
    public String getTargetTid() { return targetTid; }
    public String getTargetGid() { return targetGid; }
    public SettlementDirection getPaymentDirection() { return paymentDirection; }
    public BigDecimal getAmount() { return amount; }
}
