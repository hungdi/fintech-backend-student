package com.sparta.fintech.ledger.service;

import com.sparta.fintech.ledger.domain.DebitCreditType;
import com.sparta.fintech.ledger.domain.TransactionType;
import java.math.BigDecimal;
import java.util.List;

public record LedgerPostingCommand(
    String tid,
    String gid,
    String oid,
    String journalTid,
    String accountingLedgerTid,
    TransactionType transactionType,
    BigDecimal amount,
    String currencyCode,
    String description,
    List<AccountPosting> accountPostings,
    List<JournalPosting> journalPostings
) {

    public LedgerPostingCommand {
        accountPostings = accountPostings == null ? List.of() : List.copyOf(accountPostings);
        journalPostings = journalPostings == null ? List.of() : List.copyOf(journalPostings);
    }

    public LedgerPostingCommand(
        String tid,
        String gid,
        String oid,
        TransactionType transactionType,
        BigDecimal amount,
        String currencyCode,
        String description,
        List<AccountPosting> accountPostings,
        List<JournalPosting> journalPostings
    ) {
        this(
            tid,
            gid,
            oid,
            tid + "-JOURNAL",
            tid + "-LEDGER",
            transactionType,
            amount,
            currencyCode,
            description,
            accountPostings,
            journalPostings
        );
    }

    public record AccountPosting(
        Long accountId,
        DebitCreditType debitCreditType,
        BigDecimal amount,
        String memo,
        BigDecimal balanceAfter
    ) {
    }

    public record JournalPosting(
        String accountCode,
        Long customerAccountId,
        DebitCreditType debitCreditType,
        BigDecimal amount,
        String memo
    ) {
    }
}
