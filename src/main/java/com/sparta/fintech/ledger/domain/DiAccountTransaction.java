package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/** TODO [특강 1 / 3-1] 거래와 계좌 참조를 연결하고 거래 직후 원장잔액을 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class DiAccountTransaction extends BaseTimeEntity {

    private Long accountTransactionItemId;

    private String tid;

    private String gid;

    private String oid;

    private DmTransaction transaction;

    private DmAccount account;

    private DebitCreditType debitCreditType;

    private BigDecimal amount;

    private BigDecimal balanceAfter;

    private Instant occurredAt;

    private String memo;

    protected DiAccountTransaction() {
    }

    public DiAccountTransaction(
        String tid,
        String gid,
        String oid,
        DmTransaction transaction,
        DmAccount account,
        DebitCreditType debitCreditType,
        BigDecimal amount,
        String memo,
        BigDecimal balanceAfter
    ) {
        this(tid, gid, oid, transaction, account, debitCreditType, amount, memo, balanceAfter, Instant.now());
    }

    public DiAccountTransaction(
        String tid,
        String gid,
        String oid,
        DmTransaction transaction,
        DmAccount account,
        DebitCreditType debitCreditType,
        BigDecimal amount,
        String memo,
        BigDecimal balanceAfter,
        Instant now
    ) {
        this.tid = tid;
        this.gid = gid;
        this.oid = oid;
        this.transaction = transaction;
        this.account = account;
        this.debitCreditType = debitCreditType;
        this.amount = amount;
        this.memo = memo;
        this.balanceAfter = Objects.requireNonNull(balanceAfter, "balanceAfter");
        this.occurredAt = now;
    }

    public Long getAccountTransactionItemId() {
        return accountTransactionItemId;
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

    public DmTransaction getTransaction() {
        return transaction;
    }

    public DmAccount getAccount() {
        return account;
    }

    public DebitCreditType getDebitCreditType() {
        return debitCreditType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }

    public BigDecimal getBalanceAfter() {
        return balanceAfter;
    }

    public String getMemo() {
        return memo;
    }
}
