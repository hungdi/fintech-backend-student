package com.sparta.fintech.ledger.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

/** TODO [특강 4 / 5-3-2] 완료된 송금의 오더 ID, 금액과 완료 시각 등을 불변 기록으로 저장하고 오더별 중복 저장을 막으세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class AhTransferCompletion {

    private Long completionId;

    private Long transferOrderId;

    private String tid;

    private String gid;

    private Long customerId;

    private BigDecimal amount;

    private String currencyCode;

    private Instant completedAt;
    protected AhTransferCompletion() {}
    public AhTransferCompletion(DmTransferOrder order) {
        // TODO [특강 4 / 5-3-2] 완료된 송금의 오더 ID, 금액과 완료 시각 등을 불변 기록으로 저장하고 오더별 중복 저장을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-3-2] 완료된 송금의 오더 ID, 금액과 완료 시각 등을 불변 기록으로 저장하고 오더별 중복 저장을 막으세요.");
    }
    public Long getCompletionId() { return completionId; }
    public Long getTransferOrderId() { return transferOrderId; }
    public String getTid() { return tid; }
    public String getGid() { return gid; }
    public Long getCustomerId() { return customerId; }
    public String getCurrencyCode() { return currencyCode; }
    public BigDecimal getAmount() { return amount; }
    public Instant getCompletedAt() { return completedAt; }
}
