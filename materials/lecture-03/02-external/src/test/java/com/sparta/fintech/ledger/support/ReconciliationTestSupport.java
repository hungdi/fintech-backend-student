package com.sparta.fintech.ledger.support;

import com.sparta.fintech.ledger.reconciliation.service.ReconciliationService;

import com.sparta.fintech.ledger.domain.*;
import com.sparta.fintech.ledger.repository.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
public abstract class ReconciliationTestSupport {
    protected static final LocalDate BASE_DATE = LocalDate.of(2026, 9, 15);

    @Autowired protected ReconciliationService reconciliationService;
    @Autowired protected SiReconciliationResultRepository reconciliationResultRepository;
    @Autowired protected SsReconciliationRunRepository reconciliationRunRepository;
    @Autowired protected DmTransferOrderRepository transferOrderRepository;
    @Autowired protected DmTransactionRepository transactionRepository;
    @Autowired protected DiAccountTransactionRepository accountTransactionRepository;
    @Autowired protected LiAccountingLedgerRepository accountingLedgerRepository;
    @Autowired protected LiJournalEntryLineRepository journalEntryLineRepository;
    @Autowired protected LmJournalEntryRepository journalEntryRepository;
    @Autowired protected DmAccountBalanceRepository accountBalanceRepository;
    @Autowired protected DmAccountLimitRepository accountLimitRepository;
    @Autowired protected DmAccountRepository accountRepository;
    @Autowired protected CmCustomerRepository customerRepository;
    @Autowired protected LcLedgerAccountRepository ledgerAccountRepository;
    @Autowired protected JdbcTemplate jdbcTemplate;

    @Autowired protected javax.sql.DataSource dataSource;
    @BeforeEach
    void resetPracticeDatabase() throws Exception { TestDatabaseReset.clear(dataSource); }

    protected record ReconciliationSampleData(
        Long withdrawalAccountId, Long depositAccountId,
        Long transferTransactionId, Long transferJournalEntryId, String transferGid
    ) {}

    protected List<SiReconciliationResult> resultsFor(
        Long runId, ReconciliationTargetType type
    ) {
        return reconciliationResultRepository
            .findByReconciliationRunReconciliationRunId(runId).stream()
            .filter(item -> item.getTargetType() == type).toList();
    }

    protected List<SiReconciliationResult> mismatchesFor(
        Long runId, ReconciliationTargetType type
    ) {
        return resultsFor(runId, type).stream()
            .filter(item -> item.getResultStatus() == ReconciliationResultStatus.MISMATCH)
            .toList();
    }

    private DmTransaction completedTransaction(
        String tid, String gid, TransactionType type, String amount
    ) {
        DmTransaction transaction = new DmTransaction(
            tid, gid, null, type, new BigDecimal(amount), "KRW", "대사 테스트",
            BASE_DATE.atStartOfDay().toInstant(java.time.ZoneOffset.UTC)
        );
        transaction.complete(BASE_DATE.atTime(12, 0).toInstant(java.time.ZoneOffset.UTC));
        return transactionRepository.save(transaction);
    }

    private LmJournalEntry postedJournal(DmTransaction transaction, String voucherNo) {
        LmJournalEntry journal = new LmJournalEntry(
            transaction.getTid() + "-J", transaction.getGid(), transaction.getTid(),
            transaction, voucherNo, "대사 테스트 전표"
        );
        journal.post(BASE_DATE.atTime(12, 0).toInstant(java.time.ZoneOffset.UTC));
        return journalEntryRepository.save(journal);
    }

    private void postLine(
        LmJournalEntry journal, DmTransaction transaction, LcLedgerAccount code,
        DmAccount account, int lineNo, DebitCreditType type, String amount
    ) {
        BigDecimal value = new BigDecimal(amount);
        LiJournalEntryLine line = journalEntryLineRepository.save(new LiJournalEntryLine(
            journal, code, lineNo, type, value, "대사 테스트 상세"
        ));
        accountingLedgerRepository.save(new LiAccountingLedger(
            transaction.getTid() + "-L", transaction.getGid(), transaction.getTid(),
            journal, line, transaction, code, account, type, value, BASE_DATE
        ));
    }

    protected ReconciliationSampleData createSampleData() {
        CmCustomer fromCustomer = customerRepository.save(new CmCustomer(
            "CUST-RECON-W", "박개발", "withdraw-recon@example.com", "010-1111-1111"
        ));
        CmCustomer toCustomer = customerRepository.save(new CmCustomer(
            "CUST-RECON-D", "강동원", "deposit-recon@example.com", "010-2222-2222"
        ));
        DmAccount from = accountRepository.save(new DmAccount(
            fromCustomer, "330-001", "월세 출금 계좌", "KRW"
        ));
        DmAccount to = accountRepository.save(new DmAccount(
            toCustomer, "330-002", "월세 입금 계좌", "KRW"
        ));
        accountBalanceRepository.save(new DmAccountBalance(
            from, new BigDecimal("700"), new BigDecimal("700")
        ));
        accountBalanceRepository.save(new DmAccountBalance(
            to, new BigDecimal("300"), new BigDecimal("300")
        ));
        LcLedgerAccount cash = ledgerAccountRepository.save(new LcLedgerAccount(
            "100101", "현금", DebitCreditType.DEBIT
        ));
        LcLedgerAccount deposit = ledgerAccountRepository.save(new LcLedgerAccount(
            "210101", "고객예수금", DebitCreditType.CREDIT
        ));

        DmTransaction opening = completedTransaction(
            "T-OPENING", "G-OPENING", TransactionType.DEPOSIT, "1000"
        );
        accountTransactionRepository.save(new DiAccountTransaction(
            opening.getTid(), opening.getGid(), null, opening, from,
            DebitCreditType.CREDIT, new BigDecimal("1000"), "초기 입금", new BigDecimal("1000")
        ));
        LmJournalEntry openingJournal = postedJournal(opening, "JV-OPENING");
        postLine(openingJournal, opening, cash, null, 1, DebitCreditType.DEBIT, "1000");
        postLine(openingJournal, opening, deposit, from, 2, DebitCreditType.CREDIT, "1000");

        DmTransaction transfer = completedTransaction(
            "T-TRANSFER", "G-TRANSFER", TransactionType.TRANSFER, "300"
        );
        accountTransactionRepository.save(new DiAccountTransaction(
            transfer.getTid(), transfer.getGid(), null, transfer, from,
            DebitCreditType.DEBIT, new BigDecimal("300"), "월세 출금", new BigDecimal("700")
        ));
        accountTransactionRepository.save(new DiAccountTransaction(
            transfer.getTid(), transfer.getGid(), null, transfer, to,
            DebitCreditType.CREDIT, new BigDecimal("300"), "월세 입금", new BigDecimal("300")
        ));
        LmJournalEntry transferJournal = postedJournal(transfer, "JV-TRANSFER");
        postLine(transferJournal, transfer, deposit, from, 1, DebitCreditType.DEBIT, "300");
        postLine(transferJournal, transfer, deposit, to, 2, DebitCreditType.CREDIT, "300");

        DmTransferOrder order = new DmTransferOrder(
            "settlement-key", "request-hash", "T-TRANSFER", "G-TRANSFER",
            "T-TRANSFER-J", "T-TRANSFER-L", from, to, new BigDecimal("300"), "KRW", "OTHER-BANK"
        );
        order.complete(transfer.getTransactionId(), transferJournal.getJournalEntryId(),
            transferJournal.getVoucherNo(), BASE_DATE.atTime(12, 0).toInstant(java.time.ZoneOffset.UTC));
        transferOrderRepository.save(order);
        return new ReconciliationSampleData(
            from.getAccountId(), to.getAccountId(), transfer.getTransactionId(),
            transferJournal.getJournalEntryId(), transfer.getGid()
        );
    }

    // 다음 절부터 제공하는 @Test 메서드를 추가합니다.
}
