package com.sparta.fintech.ledger.transfer.service;

import com.sparta.fintech.ledger.domain.AccountStatus;
import com.sparta.fintech.ledger.domain.CustomerStatus;
import com.sparta.fintech.ledger.domain.DebitCreditType;
import com.sparta.fintech.ledger.domain.DmAccount;
import com.sparta.fintech.ledger.domain.DmAccountBalance;
import com.sparta.fintech.ledger.domain.DmAccountLimit;
import com.sparta.fintech.ledger.domain.DmTransferOrder;
import com.sparta.fintech.ledger.domain.LimitPeriod;
import com.sparta.fintech.ledger.domain.LimitType;
import com.sparta.fintech.ledger.domain.TransactionType;
import com.sparta.fintech.ledger.domain.TransferOrderStatus;
import com.sparta.fintech.ledger.repository.DmAccountBalanceRepository;
import com.sparta.fintech.ledger.repository.DmAccountLimitRepository;
import com.sparta.fintech.ledger.repository.DmAccountRepository;
import com.sparta.fintech.ledger.repository.DmTransferOrderRepository;
import com.sparta.fintech.ledger.service.LedgerPostingCommand;
import com.sparta.fintech.ledger.service.LedgerPostingResult;
import com.sparta.fintech.ledger.service.LedgerPostingService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Clock;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Isolation;

@Service
/** TODO [특강 2 / 6-5] 앞 절에서 작성한 검증, 잔액 Lock, 원장 저장과 완료 처리를 연결하세요. */
public class TransferProcessor {

    private static final String CUSTOMER_DEPOSIT_PAYABLE_ACCOUNT_CODE = "210101";

    private final DmTransferOrderRepository transferOrderRepository;
    private final DmAccountRepository accountRepository;
    private final DmAccountBalanceRepository accountBalanceRepository;
    private final DmAccountLimitRepository accountLimitRepository;
    private final LedgerPostingService ledgerPostingService;
    private final FinancialIdGenerator idGenerator;
    private final TransferRequestHasher requestHasher;
    private final TransferFailureHook failureHook;
    private final Clock clock;

    public TransferProcessor(
        DmTransferOrderRepository transferOrderRepository,
        DmAccountRepository accountRepository,
        DmAccountBalanceRepository accountBalanceRepository,
        DmAccountLimitRepository accountLimitRepository,
        LedgerPostingService ledgerPostingService,
        FinancialIdGenerator idGenerator,
        TransferRequestHasher requestHasher,
        TransferFailureHook failureHook,
        Clock clock
    ) {
        this.transferOrderRepository = transferOrderRepository;
        this.accountRepository = accountRepository;
        this.accountBalanceRepository = accountBalanceRepository;
        this.accountLimitRepository = accountLimitRepository;
        this.ledgerPostingService = ledgerPostingService;
        this.idGenerator = idGenerator;
        this.requestHasher = requestHasher;
        this.failureHook = failureHook;
        this.clock = clock;
    }

    public TransferResult process(TransferCommand command) {
        // TODO [특강 2 / 6-5] 앞 절에서 작성한 검증, 잔액 Lock, 원장 저장과 완료 처리를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-5] 앞 절에서 작성한 검증, 잔액 Lock, 원장 저장과 완료 처리를 연결하세요.");
    }

    public TransferResult process(TransferCommand command, TransferPolicy policy) {
        // TODO [특강 2 / 6-5] 앞 절에서 작성한 검증, 잔액 Lock, 원장 저장과 완료 처리를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-5] 앞 절에서 작성한 검증, 잔액 Lock, 원장 저장과 완료 처리를 연결하세요.");
    }

    private TransferResult handleExistingOrder(
        TransferCommand command,
        String requestHash,
        DmTransferOrder existingOrder
    ) {
        // TODO [특강 2 / 5-4] 동일 요청인지 확인하고 완료 오더의 결과를 반환하며 처리 중 요청을 구분하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-4] 동일 요청인지 확인하고 완료 오더의 결과를 반환하며 처리 중 요청을 구분하세요.");
    }

    private LedgerPostingCommand toLedgerPostingCommand(
        TransferCommand command,
        DmTransferOrder transferOrder,
        DmAccount withdrawalAccount,
        DmAccount depositAccount,
        BalancePair balances
    ) {
        // TODO [특강 2 / 6-3] 추적 ID와 두 계좌의 거래 후 원장잔액 및 분개를 원장 DTO에 전달하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-3] 추적 ID와 두 계좌의 거래 후 원장잔액 및 분개를 원장 DTO에 전달하세요.");
    }

    private BalancePair lockBalances(Long withdrawalAccountId, Long depositAccountId) {
        // TODO [특강 2 / 4-3] 계좌 ID 순서로 두 잔액의 Lock을 얻고 출금과 입금 역할을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 4-3] 계좌 ID 순서로 두 잔액의 Lock을 얻고 출금과 입금 역할을 연결하세요.");
    }

    private void validateTransferLimit(
        DmAccount withdrawalAccount, BigDecimal amount, String currencyCode, LocalDate today
    ) {
        // TODO [특강 2 / 2-2] 업무일과 통화에 유효한 1회 한도를 골라 요청 금액과 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-2] 업무일과 통화에 유효한 1회 한도를 골라 요청 금액과 비교하세요.");
    }

    private boolean isEffective(DmAccountLimit limit, LocalDate today) {
        // TODO [특강 2 / 2-2] 한도의 시작일과 종료일을 업무일과 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-2] 한도의 시작일과 종료일을 업무일과 비교하세요.");
    }

    private void validateBasic(TransferCommand command) {
        // TODO [특강 2 / 2-1] KRW, 필수 입력과 반올림 없는 금액 범위를 Service 진입점에서도 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-1] KRW, 필수 입력과 반올림 없는 금액 범위를 Service 진입점에서도 검사하세요.");
    }

    private void validateAccounts(
        TransferCommand command,
        DmAccount withdrawalAccount,
        DmAccount depositAccount
    ) {
        // TODO [특강 2 / 2-1] 서로 다른 두 계좌의 고객 상태, 계좌 상태와 요청 통화를 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-1] 서로 다른 두 계좌의 고객 상태, 계좌 상태와 요청 통화를 검사하세요.");
    }

    private void validateActive(DmAccount account, String name) {
        // TODO [특강 2 / 2-1] 고객과 계좌가 모두 거래 가능한 상태인지 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-1] 고객과 계좌가 모두 거래 가능한 상태인지 검사하세요.");
    }

    private DmAccount getAccount(String accountNo, String name) {
        // TODO [특강 2 / 2-1] 계좌번호로 계좌를 조회하고 없는 계좌의 업무 오류를 구분하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-1] 계좌번호로 계좌를 조회하고 없는 계좌의 업무 오류를 구분하세요.");
    }

    private TransferResult toResult(
        DmTransferOrder transferOrder,
        DmAccountBalance withdrawalBalance,
        DmAccountBalance depositBalance
    ) {
        // TODO [특강 2 / 6-4] 완료 오더와 잔액을 내부 결과 DTO로 변환하세요. 고객 응답 계약은 별도로 유지합니다.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-4] 완료 오더와 잔액을 내부 결과 DTO로 변환하세요. 고객 응답 계약은 별도로 유지합니다.");
    }

    private String description(TransferCommand command) {
        return command.description() == null || command.description().isBlank()
            ? "계좌 간 송금"
            : command.description();
    }

    private void requireText(String value, String name) {
        // TODO [특강 2 / 2-1] 필수 요청 키와 계좌번호, 통화가 null 또는 빈 문자열이면 거절하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 2-1] 필수 요청 키와 계좌번호, 통화가 null 또는 빈 문자열이면 거절하세요.");
    }

    private record BalancePair(
        DmAccountBalance withdrawalBalance,
        DmAccountBalance depositBalance
    ) {
    }
}
