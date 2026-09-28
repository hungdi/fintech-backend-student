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
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.Instant;

/** TODO [특강 1 / 3-1] 거래 식별자와 상태, 완료 시각을 저장하고 거래 출처를 구분하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class DmTransaction extends BaseTimeEntity {

    private Long transactionId;

    private String tid;

    private String gid;

    private String oid;

    private TransactionType transactionType;

    private TransactionOrigin transactionOrigin = TransactionOrigin.LEDGER_EXERCISE;

    private TransactionStatus transactionStatus;

    private BigDecimal requestedAmount;

    private String currencyCode;

    private Instant requestedAt;

    private Instant completedAt;

    private String description;

    protected DmTransaction() {
    }

    public DmTransaction(
        String tid,
        String gid,
        String oid,
        TransactionType transactionType,
        BigDecimal requestedAmount,
        String currencyCode,
        String description
    ) {
        this(tid, gid, oid, transactionType, requestedAmount, currencyCode, description, Instant.now());
    }

    public DmTransaction(
        String tid,
        String gid,
        String oid,
        TransactionType transactionType,
        BigDecimal requestedAmount,
        String currencyCode,
        String description,
        Instant now
    ) {
        this.tid = tid;
        this.gid = gid;
        this.oid = oid;
        this.transactionType = transactionType;
        this.transactionStatus = TransactionStatus.REQUESTED;
        this.requestedAmount = requestedAmount;
        this.currencyCode = currencyCode;
        this.requestedAt = now;
        this.description = description;
    }

    public void markTransferApi() {
        // TODO [특강 2 / 6-3] API가 만든 거래의 출처를 원장 직접 실습과 구분하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-3] API가 만든 거래의 출처를 원장 직접 실습과 구분하세요.");
    }

    public TransactionOrigin getTransactionOrigin() { return transactionOrigin; }

    public void complete() {
        complete(Instant.now());
    }

    public void complete(Instant now) {
        // TODO [특강 1 / 3-1] 거래 완료 상태와 전달받은 Instant를 함께 기록하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 3-1] 거래 완료 상태와 전달받은 Instant를 함께 기록하세요.");
    }

    public void fail() {
        // TODO [특강 1 / 3-1] 거래 실패 상태로 변경하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 3-1] 거래 실패 상태로 변경하세요.");
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public String getTid() {
        return tid;
    }

    public String getGid() {
        return gid;
    }

    public String getOid() {
        return oid;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public BigDecimal getRequestedAmount() {
        return requestedAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public String getDescription() {
        return description;
    }
}
