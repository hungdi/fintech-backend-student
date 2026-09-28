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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

/** TODO [특강 1 / 4-2] 전표 상세, 거래, 계정과목 및 고객 계좌의 관계와 불변 필드를 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class LiAccountingLedger extends BaseTimeEntity {

    private Long accountingLedgerItemId;

    private String tid;

    private String gid;

    private String oid;

    private LmJournalEntry journalEntry;

    private LiJournalEntryLine journalEntryLine;

    private DmTransaction transaction;

    private LcLedgerAccount ledgerAccount;

    private DmAccount customerAccount;

    private DebitCreditType debitCreditType;

    private BigDecimal amount;

    private LocalDate transactionDate;

    protected LiAccountingLedger() {
    }

    public LiAccountingLedger(
        String tid,
        String gid,
        String oid,
        LmJournalEntry journalEntry,
        LiJournalEntryLine journalEntryLine,
        DmTransaction transaction,
        LcLedgerAccount ledgerAccount,
        DmAccount customerAccount,
        DebitCreditType debitCreditType,
        BigDecimal amount,
        LocalDate transactionDate
    ) {
        this.tid = tid;
        this.gid = gid;
        this.oid = oid;
        this.journalEntry = journalEntry;
        this.journalEntryLine = journalEntryLine;
        this.transaction = transaction;
        this.ledgerAccount = ledgerAccount;
        this.customerAccount = customerAccount;
        this.debitCreditType = debitCreditType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public Long getAccountingLedgerItemId() {
        return accountingLedgerItemId;
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

    public LmJournalEntry getJournalEntry() {
        return journalEntry;
    }

    public LiJournalEntryLine getJournalEntryLine() {
        return journalEntryLine;
    }

    public DmTransaction getTransaction() {
        return transaction;
    }

    public LcLedgerAccount getLedgerAccount() {
        return ledgerAccount;
    }

    public DmAccount getCustomerAccount() {
        return customerAccount;
    }

    public DebitCreditType getDebitCreditType() {
        return debitCreditType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }
}
