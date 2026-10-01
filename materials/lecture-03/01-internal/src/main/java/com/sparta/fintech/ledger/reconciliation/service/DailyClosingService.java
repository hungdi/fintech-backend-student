package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.DmAccountBalance;
import com.sparta.fintech.ledger.domain.SmAccountDailyClosing;
import com.sparta.fintech.ledger.repository.DiAccountTransactionRepository;
import com.sparta.fintech.ledger.repository.DmAccountBalanceRepository;
import com.sparta.fintech.ledger.repository.SmAccountDailyClosingRepository;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/** 원장 잔액 보관과 대사 비교는 별도 Service로 구현합니다. */
@Service
public class DailyClosingService {

    private final DmAccountBalanceRepository balances;
    private final DiAccountTransactionRepository accountTransactions;
    private final SmAccountDailyClosingRepository closings;
    private final Clock clock;

    public DailyClosingService(DmAccountBalanceRepository balances,
        DiAccountTransactionRepository accountTransactions,
        SmAccountDailyClosingRepository closings, Clock clock) {
        this.balances = balances;
        this.accountTransactions = accountTransactions;
        this.closings = closings;
        this.clock = clock;
    }

    // TODO [특강 3 / 2-5-2] READ_COMMITTED 트랜잭션에서 잔액 행을 잠그세요.
    public SmAccountDailyClosing initializeOpeningBalance(Long accountId, LocalDate openingDate) {
        // 미래 날짜, 개설일 이전 날짜, 기존 거래/마감/activeBusinessDate가 있는 계좌는 거절하세요.
        // 최초 거래 전에 확인한 실제 원장 잔액을 openingDate 전날의 명시적인 기준으로 등록하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-5-2] 최초 거래 전 기초 잔액을 등록하세요.");
    }

    // TODO [특강 3 / 2-5-2] 기존 송금 트랜잭션이 반드시 있어야 하는 전파 속성을 적용하세요.
    public void prepareForPosting(DmAccountBalance balance, Instant postedAt) {
        // 호출자는 잔액 행을 잠근 상태에서 잔액 변경 전에 호출합니다.
        // 미래 시각/이미 마감한 날짜의 거래를 거절하고, 새 날짜의 첫 거래 전에 이전 날짜의 실제 잔액을 보관하세요.
        // activeBusinessDate가 없으면 첫 거래일만 기록합니다. 확인하지 않은 기초 잔액을 자동 VERIFIED로 만들지 마세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-5-2] 잔액 변경 전에 날짜와 마감을 준비하세요.");
    }

    // TODO [특강 3 / 2-5-2] READ_COMMITTED 트랜잭션에서 계좌 ID 순서로 잔액 행을 잠그세요.
    public int capture(LocalDate closedDate) {
        // 종료된 UTC 날짜만 허용하며 대상일 종료 전에 개설된 계좌를 포함합니다.
        // 거래 없는 날짜도 보관하고 기존 금액/보관 시각은 덮어쓰지 않습니다. 반환값은 새로 만든 마감 행 수입니다.
        // 현재 잔액으로 과거 마감을 복원하거나, 기초 등록이 없는 계좌를 자동 승인하지 마세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-5-2] 종료된 날짜까지 실제 원장 잔액을 보관하세요.");
    }

    private int captureThrough(DmAccountBalance balance, LocalDate closedDate, Instant now) {
        // TODO [특강 3 / 2-5-2] activeBusinessDate부터 빈 날짜를 채우고 활성 날짜를 단조 증가시키세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-5-2] 거래 없는 날짜와 활성 날짜를 처리하세요.");
    }

    private void requireExistingCapture(DmAccountBalance balance, LocalDate date) {
        // TODO [특강 3 / 2-5-2] 해당 날짜의 원본 마감이 있는지 확인하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 2-5-2] 기존 마감 기록을 확인하세요.");
    }

    private LocalDate today() {
        return clock.instant().atOffset(ZoneOffset.UTC).toLocalDate();
    }
}
