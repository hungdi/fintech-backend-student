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
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.Instant;

/** TODO [특강 2 / 5-2-1] 요청 키의 유일성, 계좌 참조, 완료 정보 및 Instant 시각을 구현하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class DmTransferOrder extends BaseTimeEntity {

    private Long transferOrderId;

    private String idempotencyKey;

    private String requestHash;

    private String tid;

    private String gid;

    private String journalTid;

    private String accountingLedgerTid;

    private DmAccount withdrawalAccount;

    private DmAccount depositAccount;

    private BigDecimal amount;

    private String currencyCode;

    private TransferOrderStatus transferOrderStatus;

    private Long transactionId;

    private Long journalEntryId;

    private String voucherNo;

    private Instant completedAt;

    private String failureReason;

    protected DmTransferOrder() {
    }

    public DmTransferOrder(
        String idempotencyKey,
        String requestHash,
        String tid,
        String gid,
        String journalTid,
        String accountingLedgerTid,
        DmAccount withdrawalAccount,
        DmAccount depositAccount,
        BigDecimal amount,
        String currencyCode
    ) {
        this.idempotencyKey = idempotencyKey;
        this.requestHash = requestHash;
        this.tid = tid;
        this.gid = gid;
        this.journalTid = journalTid;
        this.accountingLedgerTid = accountingLedgerTid;
        this.withdrawalAccount = withdrawalAccount;
        this.depositAccount = depositAccount;
        this.amount = amount;
        this.currencyCode = currencyCode;
        this.transferOrderStatus = TransferOrderStatus.PROCESSING;
    }

    public void complete(Long transactionId, Long journalEntryId, String voucherNo) {
        complete(transactionId, journalEntryId, voucherNo, Instant.now());
    }

    public void complete(Long transactionId, Long journalEntryId, String voucherNo, Instant now) {
        // TODO [특강 2 / 5-2-2] null 완료 시각을 상태·링크·번호 변경 전에 거절하고 PK, 전표 번호, 상태 및 Instant를 기록하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-2-2] 거래 PK, 전표 PK와 번호, 완료 상태 및 전달된 Instant를 기록하세요.");
    }

    public void fail(String failureReason) {
        // TODO [특강 2 / 5-2-2] 실패 사유와 오더 실패 상태를 함께 기록하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-2-2] 실패 사유와 오더 실패 상태를 함께 기록하세요.");
    }

    public Long getTransferOrderId() {
        return transferOrderId;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getRequestHash() {
        return requestHash;
    }

    public String getTid() {
        return tid;
    }

    public String getGid() {
        return gid;
    }

    public String getJournalTid() {
        return journalTid;
    }

    public String getAccountingLedgerTid() {
        return accountingLedgerTid;
    }

    public DmAccount getWithdrawalAccount() {
        return withdrawalAccount;
    }

    public DmAccount getDepositAccount() {
        return depositAccount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public TransferOrderStatus getTransferOrderStatus() {
        return transferOrderStatus;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public Long getJournalEntryId() {
        return journalEntryId;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public String getFailureReason() {
        return failureReason;
    }
}
