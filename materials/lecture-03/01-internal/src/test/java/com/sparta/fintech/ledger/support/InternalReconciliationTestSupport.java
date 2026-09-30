package com.sparta.fintech.ledger.support;

import com.sparta.fintech.ledger.reconciliation.service.ReconciliationService;
import com.sparta.fintech.ledger.reconciliation.service.DailyClosingService;

import com.sparta.fintech.ledger.domain.*;
import com.sparta.fintech.ledger.repository.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

/** MATERIALS.md의 3-2 보완 계약을 기존 DmAccountBalance에 추가한 뒤 사용할 테스트 지원입니다. */
@ActiveProfiles("test")
@SpringBootTest
@Import(InternalReconciliationTestSupport.TimeConfiguration.class)
public abstract class InternalReconciliationTestSupport {
    protected static final LocalDate BASE_DATE = LocalDate.of(2026, 9, 15);
    protected static final Instant POSTED_AT = Instant.parse("2026-09-15T12:00:00Z");
    protected static final Instant RECONCILIATION_TIME = Instant.parse("2026-09-16T02:00:00Z");

    @TestConfiguration(proxyBeanMethods = false)
    public static class TimeConfiguration {
        @Bean
        @Primary
        MutableBusinessClock reconciliationPracticeClock() {
            return new MutableBusinessClock(RECONCILIATION_TIME);
        }
    }

    @Autowired protected ReconciliationService reconciliationService;
    @Autowired protected DailyClosingService dailyClosingService;
    @Autowired protected SmAccountDailyClosingRepository dailyClosingRepository;
    @Autowired protected MutableBusinessClock practiceClock;
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
    void resetPracticeDatabase() throws Exception {
        practiceClock.set(RECONCILIATION_TIME);
        TestDatabaseReset.clear(dataSource);
    }

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
            POSTED_AT
        );
        transaction.complete(POSTED_AT);
        return transactionRepository.save(transaction);
    }

    private LmJournalEntry postedJournal(DmTransaction transaction, String voucherNo) {
        LmJournalEntry journal = new LmJournalEntry(
            transaction.getTid() + "-J", transaction.getGid(), transaction.getTid(),
            transaction, voucherNo, "대사 테스트 전표"
        );
        journal.post(POSTED_AT);
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

    /**
     * 3-2 확장의 마감 엔티티·업무일 과제를 완성한 뒤 사용합니다.
     * 검증된 기초 잔액과 보관된 당일 잔액을 테스트 입력으로 직접 준비합니다.
     * 마감 서비스의 합산·검증 로직은 이 준비 코드에 구현하지 않습니다.
     */
    protected ReconciliationSampleData createSampleData() {
        CmCustomer fromCustomer = customerRepository.save(new CmCustomer(
            "CUST-RECON-W", "박개발", "withdraw-recon@example.com", "010-1111-1111"
        ));
        CmCustomer toCustomer = customerRepository.save(new CmCustomer(
            "CUST-RECON-D", "강동원", "deposit-recon@example.com", "010-2222-2222"
        ));
        DmAccount from = accountRepository.save(new DmAccount(
            fromCustomer, "330-001", "월세 출금 계좌", "KRW", BASE_DATE.atStartOfDay(ZoneOffset.UTC).toInstant()
        ));
        DmAccount to = accountRepository.save(new DmAccount(
            toCustomer, "330-002", "월세 입금 계좌", "KRW", BASE_DATE.atStartOfDay(ZoneOffset.UTC).toInstant()
        ));
        prepareClosingInputs(from, new BigDecimal("700"));
        prepareClosingInputs(to, new BigDecimal("300"));
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
            DebitCreditType.CREDIT, new BigDecimal("1000"), "초기 입금", new BigDecimal("1000"), POSTED_AT
        ));
        LmJournalEntry openingJournal = postedJournal(opening, "JV-OPENING");
        postLine(openingJournal, opening, cash, null, 1, DebitCreditType.DEBIT, "1000");
        postLine(openingJournal, opening, deposit, from, 2, DebitCreditType.CREDIT, "1000");

        DmTransaction transfer = completedTransaction(
            "T-TRANSFER", "G-TRANSFER", TransactionType.TRANSFER, "300"
        );
        accountTransactionRepository.save(new DiAccountTransaction(
            transfer.getTid(), transfer.getGid(), null, transfer, from,
            DebitCreditType.DEBIT, new BigDecimal("300"), "월세 출금", new BigDecimal("700"), POSTED_AT
        ));
        accountTransactionRepository.save(new DiAccountTransaction(
            transfer.getTid(), transfer.getGid(), null, transfer, to,
            DebitCreditType.CREDIT, new BigDecimal("300"), "월세 입금", new BigDecimal("300"), POSTED_AT
        ));
        LmJournalEntry transferJournal = postedJournal(transfer, "JV-TRANSFER");
        postLine(transferJournal, transfer, deposit, from, 1, DebitCreditType.DEBIT, "300");
        postLine(transferJournal, transfer, deposit, to, 2, DebitCreditType.CREDIT, "300");

        return new ReconciliationSampleData(
            from.getAccountId(), to.getAccountId(), transfer.getTransactionId(),
            transferJournal.getJournalEntryId(), transfer.getGid()
        );
    }

    private void prepareClosingInputs(DmAccount account, BigDecimal closingAmount) {
        var balance = new DmAccountBalance(account, closingAmount, closingAmount);
        balance.initializeBusinessDate(BASE_DATE.plusDays(1));
        accountBalanceRepository.save(balance);
        // openingBaseline의 VERIFIED 초기화 규칙도 학생 구현 대상입니다.
        dailyClosingRepository.save(SmAccountDailyClosing.openingBaseline(
            account, BASE_DATE.minusDays(1), BigDecimal.ZERO, POSTED_AT));
        dailyClosingRepository.save(new SmAccountDailyClosing(account, BASE_DATE, closingAmount,
            BASE_DATE.plusDays(1).atTime(0, 10).toInstant(ZoneOffset.UTC)));
    }

    // 다음 절부터 제공하는 @Test 메서드를 추가합니다.
}
