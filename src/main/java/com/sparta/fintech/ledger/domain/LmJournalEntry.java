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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;

/** TODO [특강 1 / 4-2] 거래와 전표의 관계 및 전표 확정 상태를 구현하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class LmJournalEntry extends BaseTimeEntity {

    private Long journalEntryId;

    private String tid;

    private String gid;

    private String oid;

    private DmTransaction transaction;

    private String voucherNo;

    private JournalStatus journalStatus;

    private String description;

    private Instant postedAt;

    protected LmJournalEntry() {
    }

    public LmJournalEntry(
        String tid,
        String gid,
        String oid,
        DmTransaction transaction,
        String voucherNo,
        String description
    ) {
        this.tid = tid;
        this.gid = gid;
        this.oid = oid;
        this.transaction = transaction;
        this.voucherNo = voucherNo;
        this.description = description;
        this.journalStatus = JournalStatus.DRAFT;
    }

    public void post() {
        post(Instant.now());
    }

    public void post(Instant now) {
        // TODO [특강 1 / 4-2] 거래와 전표의 관계 및 전표 확정 상태를 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 거래와 전표의 관계 및 전표 확정 상태를 구현하세요.");
    }

    public Long getJournalEntryId() {
        return journalEntryId;
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

    public String getVoucherNo() {
        return voucherNo;
    }

    public JournalStatus getJournalStatus() {
        return journalStatus;
    }

    public String getDescription() {
        return description;
    }

    public Instant getPostedAt() {
        return postedAt;
    }
}
