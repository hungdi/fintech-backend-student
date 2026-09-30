package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.DebitCreditType;
import com.sparta.fintech.ledger.domain.DiAccountTransaction;
import com.sparta.fintech.ledger.domain.SmAccountDailyClosing;
import com.sparta.fintech.ledger.domain.DailyClosingStatus;
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
import com.sparta.fintech.ledger.repository.SmAccountDailyClosingRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;

@Service
/** TODO [특강 3 / 3-8] 대상일의 대사 결과와 마감 검증 상태를 같은 트랜잭션에서 저장하세요. */
public class ReconciliationService {

    private final Clock clock;
    private final SmAccountDailyClosingRepository dailyClosingRepository;
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
        Clock clock,
        SmAccountDailyClosingRepository dailyClosingRepository
    ) {
        this.clock = clock;
        this.dailyClosingRepository = dailyClosingRepository;
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
        // TODO [특강 3 / 3-8 일별 마감 보완] READ_COMMITTED 트랜잭션에서 과거 UTC 날짜만 허용하세요.
        // 전일 VERIFIED 마감과 대상일 보관 기록을 순서대로 잠그고 모든 대상 계좌의 기록이 있는지 검사하세요.
        // 각 비교 규칙은 대상일 범위만 조회합니다. 실행/결과 저장과 마감 검증 상태 변경을 함께 커밋하세요.
        // 불일치가 없으면 대상일 마감을 검증하고, 불일치가 있으면 해당일 및 이후 날짜의 검증을 취소하세요.
        // 재실행은 새 실행 이력을 남기며 기존 마감 금액은 변경하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-8 일별 마감 보완] 일별 대사 결과와 마감 검증 상태를 저장하세요.");
    }

    private void reconcileAccountBalanceWithAccountTransactions(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SmAccountDailyClosing> closings,
        Map<Long, BigDecimal> openingBalances,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-2 일별 마감 보완] 전일 검증 완료 마감 + 대상일 입금 - 출금을 대상일 원장 마감과 비교하세요.
        // 시작 시각은 포함하고 다음 날 00:00 UTC는 제외하세요. 실행 시점의 현재 잔액을 비교하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-2 일별 마감 보완] 전일 마감과 당일 입출금으로 기대 잔액을 계산하세요.");
    }

    private Instant dayStart(LocalDate date) {
        return date.atStartOfDay(ZoneOffset.UTC).toInstant();
    }

    private Instant dayEnd(LocalDate date) {
        return date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();
    }

    private List<DmTransaction> completedTransactions(LocalDate date) {
        // TODO [특강 3 / 3-3 일별 마감 보완] 완료 시각이 대상일 범위에 속한 거래만 조회하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-3 일별 마감 보완] 날짜 범위의 완료 거래를 조회하세요.");
    }

    private List<DmTransaction> lifecycleTransactions(LocalDate date) {
        // TODO [특강 3 / 3-7-1 일별 마감 보완] 날짜 범위의 완료/미완료 요청/계좌거래/전표 후보를 조회하고 거래 ID로 중복을 제거하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-7-1 일별 마감 보완] 대상일의 거래 생명주기 검사 후보를 조회하세요.");
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
        // TODO [특강 3 / 3-3] 거래 유형과 API 오더의 계좌에 맞는 계좌거래 쌍을 확인하세요. 발생일은 거래 완료 UTC 날짜와 같아야 합니다.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-3] 계좌거래의 금액, 방향, 계좌와 발생일을 검사하세요.");
    }

    private boolean hasMatchingLedgerLines(LmJournalEntry journal, List<LiJournalEntryLine> lines,
                                          List<LiAccountingLedger> ledgers) {
        // TODO [특강 3 / 3-5] 요청 금액, 전표 상세와 원장 참조, 고객 계좌와 transactionDate를 대조하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-5] 분개 금액과 고객 계좌 및 거래일을 대조하세요. 6-3에서는 추가 오류 사례를 검증합니다.");
    }

    private BigDecimal journalDebitAmount(LmJournalEntry journal) {
        // TODO [특강 3 / 3-4] 해당 전표의 차변 상세 금액 합계를 구하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-4] 해당 전표의 차변 상세 금액 합계를 구하세요.");
    }

    private boolean hasMatchingJournalAmount(LmJournalEntry journal) {
        // TODO [특강 3 / 3-4] 요청 금액, 지원 유형의 차대 방향/행 수와 전표 저장 UTC 날짜를 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-4] 분개 금액과 구조 및 전표 저장일을 검사하세요.");
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
