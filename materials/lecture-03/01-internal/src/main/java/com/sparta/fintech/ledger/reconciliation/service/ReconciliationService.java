package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.DebitCreditType;
import com.sparta.fintech.ledger.domain.DiAccountTransaction;
import com.sparta.fintech.ledger.domain.DmAccountBalance;
import com.sparta.fintech.ledger.domain.DmTransaction;
import com.sparta.fintech.ledger.domain.DmTransferOrder;
import com.sparta.fintech.ledger.domain.LiAccountingLedger;
import com.sparta.fintech.ledger.domain.LiJournalEntryLine;
import com.sparta.fintech.ledger.domain.LmJournalEntry;
import com.sparta.fintech.ledger.domain.ReconciliationResultStatus;
import com.sparta.fintech.ledger.domain.ReconciliationTargetType;
import com.sparta.fintech.ledger.domain.SiReconciliationResult;
import com.sparta.fintech.ledger.domain.SsReconciliationRun;
import com.sparta.fintech.ledger.domain.TransactionStatus;
import com.sparta.fintech.ledger.domain.TransactionType;
import com.sparta.fintech.ledger.domain.LedgerPostingRules;
import com.sparta.fintech.ledger.repository.DiAccountTransactionRepository;
import com.sparta.fintech.ledger.repository.DmAccountBalanceRepository;
import com.sparta.fintech.ledger.repository.DmTransactionRepository;
import com.sparta.fintech.ledger.repository.DmTransferOrderRepository;
import com.sparta.fintech.ledger.repository.LiAccountingLedgerRepository;
import com.sparta.fintech.ledger.repository.LiJournalEntryLineRepository;
import com.sparta.fintech.ledger.repository.LmJournalEntryRepository;
import com.sparta.fintech.ledger.repository.SiReconciliationResultRepository;
import com.sparta.fintech.ledger.repository.SsReconciliationRunRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Clock;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 3 / 3-8] 각 비교 규칙을 실행하고 비교 결과와 실행 헤더를 함께 저장하세요. */
public class ReconciliationService {

    private final Clock clock;
    private final SsReconciliationRunRepository reconciliationRunRepository;
    private final SiReconciliationResultRepository reconciliationResultRepository;
    private final DmAccountBalanceRepository accountBalanceRepository;
    private final DiAccountTransactionRepository accountTransactionRepository;
    private final DmTransactionRepository transactionRepository;
    private final DmTransferOrderRepository transferOrderRepository;
    private final LmJournalEntryRepository journalEntryRepository;
    private final LiJournalEntryLineRepository journalEntryLineRepository;
    private final LiAccountingLedgerRepository accountingLedgerRepository;

    public ReconciliationService(
        SsReconciliationRunRepository reconciliationRunRepository,
        SiReconciliationResultRepository reconciliationResultRepository,
        DmAccountBalanceRepository accountBalanceRepository,
        DiAccountTransactionRepository accountTransactionRepository,
        DmTransactionRepository transactionRepository,
        DmTransferOrderRepository transferOrderRepository,
        LmJournalEntryRepository journalEntryRepository,
        LiJournalEntryLineRepository journalEntryLineRepository,
        LiAccountingLedgerRepository accountingLedgerRepository,
        Clock clock
    ) {
        this.clock = clock;
        this.reconciliationRunRepository = reconciliationRunRepository;
        this.reconciliationResultRepository = reconciliationResultRepository;
        this.accountBalanceRepository = accountBalanceRepository;
        this.accountTransactionRepository = accountTransactionRepository;
        this.transactionRepository = transactionRepository;
        this.transferOrderRepository = transferOrderRepository;
        this.journalEntryRepository = journalEntryRepository;
        this.journalEntryLineRepository = journalEntryLineRepository;
        this.accountingLedgerRepository = accountingLedgerRepository;
    }

    public ReconciliationRunResult run(LocalDate baseDate) {
        // TODO [특강 3 / 3-8] 각 비교 규칙을 실행하고 비교 결과와 실행 헤더를 함께 저장하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-8] 각 비교 규칙을 실행하고 비교 결과와 실행 헤더를 함께 저장하세요.");
    }

    private void reconcileAccountBalanceWithAccountTransactions(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-2] 완료 거래의 누적 입출금으로 구한 잔액과 현재 원장잔액을 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-2] 완료 거래의 누적 입출금으로 구한 잔액과 현재 원장잔액을 비교하세요.");
    }

    private void reconcileTransactionsWithAccountTransactions(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-3] 요청 금액과 유형에 맞는 계좌거래의 금액, 방향 및 실제 계좌를 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-3] 요청 금액과 유형에 맞는 계좌거래의 금액, 방향 및 실제 계좌를 비교하세요.");
    }

    private void reconcileTransactionsWithJournalEntries(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-4] 거래에 연결된 전표의 존재와 상태, 각 분개 금액을 요청 금액과 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-4] 거래에 연결된 전표의 존재와 상태, 각 분개 금액을 요청 금액과 비교하세요.");
    }

    private void reconcileJournalEntriesWithAccountingLedgers(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-5] 전표 상세와 회계원장의 행 수, 참조, 금액, 방향 및 고객 계좌를 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-5] 전표 상세와 회계원장의 행 수, 참조, 금액, 방향 및 고객 계좌를 비교하세요.");
    }

    private void reconcileAccountingLedgerDebitsAndCredits(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-6] 회계원장의 차변 합계와 대변 합계를 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-6] 회계원장의 차변 합계와 대변 합계를 비교하세요.");
    }

    private void reconcileCompletedTransactionsMissingLedgerOrJournal(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-7] 완료 거래에서 전표나 회계원장 전체가 빠진 경우를 찾으세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-7] 완료 거래에서 전표나 회계원장 전체가 빠진 경우를 찾으세요.");
    }

    private void reconcileTransactionLifecycle(SsReconciliationRun run, LocalDate date, List<SiReconciliationResult> results) {
        // TODO [특강 3 / 3-7-1] 완료 시각과 상태, API 거래 출처에 필요한 송금 오더를 확인하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-7-1] 완료 시각과 상태, API 거래 출처에 필요한 송금 오더를 확인하세요.");
    }

    private boolean hasCompleteAccountItems(DmTransaction transaction, List<DiAccountTransaction> items) {
        // TODO [특강 3 / 3-3] 거래 유형과 API 오더의 계좌에 맞는 계좌거래 쌍을 확인하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-3] 거래 유형과 API 오더의 계좌에 맞는 계좌거래 쌍을 확인하세요.");
    }

    private boolean hasMatchingLedgerLines(LmJournalEntry journal, List<LiJournalEntryLine> lines,
                                          List<LiAccountingLedger> ledgers) {
        // TODO [특강 3 / 6-3] 금액과 참조가 일치해도 실제 출금 및 입금 고객 계좌가 다르면 불일치로 판정하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 6-3] 금액과 참조가 일치해도 실제 출금 및 입금 고객 계좌가 다르면 불일치로 판정하세요.");
    }

    private BigDecimal journalDebitAmount(LmJournalEntry journal) {
        // TODO [특강 3 / 3-4] 해당 전표의 차변 상세 금액 합계를 구하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-4] 해당 전표의 차변 상세 금액 합계를 구하세요.");
    }

    private boolean hasMatchingJournalAmount(LmJournalEntry journal) {
        // TODO [특강 3 / 3-4] 요청 금액과 각 분개 금액을 비교하고 지원 유형의 차대 방향과 행 수를 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-4] 요청 금액과 각 분개 금액을 비교하고 지원 유형의 차대 방향과 행 수를 검사하세요.");
    }

    private BigDecimal accountTransactionBalance(List<DiAccountTransaction> items) {
        // TODO [특강 3 / 3-2] 완료 거래의 입금과 출금을 구분해 기대 원장잔액을 계산하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-2] 완료 거래의 입금과 출금을 구분해 기대 원장잔액을 계산하세요.");
    }

    private BigDecimal transactionExpectedAccountTransactionAmount(DmTransaction transaction) {
        // TODO [특강 3 / 3-3] 송금, 입금, 출금 유형에 필요한 계좌거래 금액의 합계를 구하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-3] 송금, 입금, 출금 유형에 필요한 계좌거래 금액의 합계를 구하세요.");
    }

    private BigDecimal transactionActualAccountTransactionAmount(
        DmTransaction transaction,
        List<DiAccountTransaction> items
    ) {
        // TODO [특강 3 / 3-3] 해당 거래와 연결된 계좌거래 금액의 실제 합계를 구하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-3] 해당 거래와 연결된 계좌거래 금액의 실제 합계를 구하세요.");
    }

    private BigDecimal sumAccountTransactions(List<DiAccountTransaction> items, DebitCreditType type) {
        return items.stream()
            .filter(item -> item.getDebitCreditType() == type)
            .map(DiAccountTransaction::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal sumLines(List<LiJournalEntryLine> lines) {
        return lines.stream()
            .map(LiJournalEntryLine::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal sumLedgers(List<LiAccountingLedger> ledgers) {
        return ledgers.stream()
            .map(LiAccountingLedger::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal sumLedgers(List<LiAccountingLedger> ledgers, DebitCreditType type) {
        return ledgers.stream()
            .filter(ledger -> ledger.getDebitCreditType() == type)
            .map(LiAccountingLedger::getAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private SiReconciliationResult result(
        SsReconciliationRun run,
        ReconciliationTargetType targetType,
        LocalDate baseDate,
        String targetTid,
        String targetGid,
        Long accountId,
        BigDecimal expectedAmount,
        BigDecimal actualAmount,
        String reason
    ) {
        return result(run, targetType, baseDate, targetTid, targetGid, accountId,
            expectedAmount, actualAmount, true, reason);
    }

    private SiReconciliationResult result(
        SsReconciliationRun run, ReconciliationTargetType targetType, LocalDate baseDate,
        String targetTid, String targetGid, Long accountId,
        BigDecimal expectedAmount, BigDecimal actualAmount, boolean structureMatches, String reason
    ) {
        return new SiReconciliationResult(
            run,
            targetType,
            baseDate,
            targetTid,
            targetGid,
            accountId,
            expectedAmount,
            actualAmount,
            structureMatches,
            reason
        );
    }
}
