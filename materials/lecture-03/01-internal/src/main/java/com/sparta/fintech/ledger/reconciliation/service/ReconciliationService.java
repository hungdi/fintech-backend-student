package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.DebitCreditType;
import com.sparta.fintech.ledger.domain.DiAccountTransaction;
import com.sparta.fintech.ledger.domain.SmAccountDailyClosing;
import com.sparta.fintech.ledger.domain.DailyClosingStatus;
import com.sparta.fintech.ledger.domain.DmTransaction;
import com.sparta.fintech.ledger.domain.DmTransferOrder;
import com.sparta.fintech.ledger.domain.LiAccountingLedger;
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
/** TODO [특강 3 / 3-5] 대상일의 대사 결과와 마감 검증 상태를 같은 트랜잭션에서 저장하세요. */
public class ReconciliationService {

    private final Clock clock;
    private final SmAccountDailyClosingRepository dailyClosingRepository;
    private final SsReconciliationRunRepository reconciliationRunRepository;
    private final SiReconciliationResultRepository reconciliationResultRepository;
    private final DmAccountBalanceRepository accountBalanceRepository;
    private final DiAccountTransactionRepository accountTransactionRepository;
    private final DmTransactionRepository transactionRepository;
    private final DmTransferOrderRepository transferOrderRepository;
    private final LiAccountingLedgerRepository accountingLedgerRepository;

    public ReconciliationService(
        SsReconciliationRunRepository reconciliationRunRepository,
        SiReconciliationResultRepository reconciliationResultRepository,
        DmAccountBalanceRepository accountBalanceRepository,
        DiAccountTransactionRepository accountTransactionRepository,
        DmTransactionRepository transactionRepository,
        DmTransferOrderRepository transferOrderRepository,
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
        this.accountingLedgerRepository = accountingLedgerRepository;
    }

    public ReconciliationRunResult run(LocalDate baseDate) {
        // TODO [특강 3 / 3-5] READ_COMMITTED 트랜잭션에서 과거 UTC 날짜만 허용하세요.
        // 전일 VERIFIED 마감과 대상일 보관 기록을 순서대로 잠그고 모든 대상 계좌의 기록이 있는지 검사하세요.
        // 기준일 완료 거래를 조회하고 연결된 회계원장은 거래 ID로 조회해 잘못된 날짜도 검사합니다. 실행/결과 저장과 마감 검증 상태 변경을 함께 커밋하세요.
        // reconcileAccountBalanceWithAccountTransactions, reconcileTransactionsWithAccountTransactions,
        // reconcileAccountTransactionsWithAccountingLedgers의 세 가지 대사만 호출하세요.
        // 실행이 정상 종료되면 불일치 유무와 관계없이 run.complete로 COMPLETED를 기록하세요.
        // 정상 샘플은 유형별 2건씩 총 6건, 불일치 0건입니다.
        // 불일치가 없으면 대상일 마감을 검증하고, 불일치가 있으면 해당일 및 이후 날짜의 검증을 취소하세요.
        // 재실행은 새 실행 이력을 남기며 기존 마감 금액은 변경하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-5] 일별 대사 결과와 마감 검증 상태를 저장하세요.");
    }

    private void reconcileAccountBalanceWithAccountTransactions(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SmAccountDailyClosing> closings,
        Map<Long, BigDecimal> openingBalances,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-2] 전일 검증 완료 마감 + 대상일 입금 - 출금을 대상일 원장 마감과 비교하세요.
        // 시작 시각은 포함하고 다음 날 00:00 UTC는 제외하세요. 실행 시점의 현재 잔액을 비교하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-2] 전일 마감과 당일 입출금으로 기대 잔액을 계산하세요.");
    }

    private Instant dayStart(LocalDate date) {
        return date.atStartOfDay(ZoneOffset.UTC).toInstant();
    }

    private Instant dayEnd(LocalDate date) {
        return date.plusDays(1).atStartOfDay(ZoneOffset.UTC).toInstant();
    }

    private List<DmTransaction> completedTransactions(LocalDate date) {
        // TODO [특강 3 / 3-1-2] 완료 시각이 대상일 범위에 속한 거래만 조회하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-1-2] 날짜 범위의 완료 거래를 조회하세요.");
    }

    private void reconcileTransactionsWithAccountTransactions(
        SsReconciliationRun run,
        LocalDate baseDate,
        List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-3] 요청 금액과 유형에 맞는 계좌거래의 금액, 방향 및 실제 계좌를 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-3] 요청 금액과 유형에 맞는 계좌거래의 금액, 방향 및 실제 계좌를 비교하세요.");
    }

    private void reconcileAccountTransactionsWithAccountingLedgers(
        SsReconciliationRun run, LocalDate baseDate, List<SiReconciliationResult> results
    ) {
        // TODO [특강 3 / 3-4] 완료 거래 ID로 계좌원장과 회계원장을 조회해 합계와 개별 내역을 비교하세요.
        // 회계원장은 날짜로 제한하지 않아 잘못된 transactionDate도 검사합니다.
        // ACCOUNT_TRANSACTION_VS_ACCOUNTING_LEDGER 결과의 대상 TID/GID는 거래에서 가져옵니다.
        // 현금 입출금의 기대 합계에는 계좌 내역에 대응하는 현금 분개 금액도 포함하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-4] 계좌원장과 회계원장을 거래별로 직접 비교하세요.");
    }

    private BigDecimal expectedAccountingLedgerAmount(DmTransaction transaction, List<DiAccountTransaction> items) {
        // TODO [특강 3 / 3-4] 송금은 입출금 합계, 현금 입출금은 계좌 내역과 현금 대응분의 합계를 구하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-4] 회계원장 기대 합계를 구하세요.");
    }

    private BigDecimal sumAccountingLedgers(List<LiAccountingLedger> ledgers) {
        return ledgers.stream().map(LiAccountingLedger::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private boolean hasCompleteAccountItems(DmTransaction transaction, List<DiAccountTransaction> items) {
        // TODO [특강 3 / 3-3] 생성 경로에 관계없이 거래 유형, 양수 요청 금액, 입출금 건수·방향·금액·계좌를 확인하세요.
        // 입금은 CREDIT 1건, 출금은 DEBIT 1건이며 각 금액은 요청액과 같아야 합니다.
        // 송금은 서로 다른 계좌의 DEBIT 1건과 CREDIT 1건, 각 금액이 요청액과 같은 구조여야 합니다.
        // 송금 오더가 존재하고 출금·입금 계좌가 각각 일치해야 합니다. 오더의 상태나 완료 시각은 대사하지 않습니다.
        // 각 계좌 통화는 거래 통화와 같고 발생일은 거래 완료 UTC 날짜와 같아야 합니다. 합계 일치만으로 정상 처리하지 마세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-3] 계좌거래의 금액, 방향, 계좌와 발생일을 검사하세요.");
    }

    private boolean hasMatchingAccountingLedgers(DmTransaction transaction, List<DiAccountTransaction> items,
                                                 List<LiAccountingLedger> ledgers) {
        // TODO [특강 3 / 3-4] 빈 목록, 거래 연결, 완료 UTC 날짜와 통화를 확인하세요.
        // LedgerPostingRules.matches로 거래 유형, 요청액, 두 원장의 개별 금액, 방향, 계좌와 계정과목을 검사하세요.
        // 합계가 같아도 내역이 다르면 false입니다. 전표 상세의 금액을 비교 기준으로 사용하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 3 / 3-4] 거래별 회계원장의 구조와 날짜를 검증하세요.");
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
