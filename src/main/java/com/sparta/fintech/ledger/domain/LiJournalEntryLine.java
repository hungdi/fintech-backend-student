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

/** TODO [특강 1 / 4-2] 전표와 계정과목의 관계 및 전표 안의 행 번호 제약을 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class LiJournalEntryLine extends BaseTimeEntity {

    private Long journalEntryLineItemId;

    private LmJournalEntry journalEntry;

    private LcLedgerAccount ledgerAccount;

    private int lineNo;

    private DebitCreditType debitCreditType;

    private BigDecimal amount;

    private String memo;

    protected LiJournalEntryLine() {
    }

    public LiJournalEntryLine(
        LmJournalEntry journalEntry,
        LcLedgerAccount ledgerAccount,
        int lineNo,
        DebitCreditType debitCreditType,
        BigDecimal amount,
        String memo
    ) {
        this.journalEntry = journalEntry;
        this.ledgerAccount = ledgerAccount;
        this.lineNo = lineNo;
        this.debitCreditType = debitCreditType;
        this.amount = amount;
        this.memo = memo;
    }

    public Long getJournalEntryLineItemId() {
        return journalEntryLineItemId;
    }

    public LmJournalEntry getJournalEntry() {
        return journalEntry;
    }

    public LcLedgerAccount getLedgerAccount() {
        return ledgerAccount;
    }

    public int getLineNo() {
        return lineNo;
    }

    public DebitCreditType getDebitCreditType() {
        return debitCreditType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getMemo() {
        return memo;
    }
}
