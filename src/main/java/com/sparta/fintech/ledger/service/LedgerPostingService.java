package com.sparta.fintech.ledger.service;

import com.sparta.fintech.ledger.domain.DmAccount;
import com.sparta.fintech.ledger.domain.DiAccountTransaction;
import com.sparta.fintech.ledger.domain.LiAccountingLedger;
import com.sparta.fintech.ledger.domain.DebitCreditType;
import com.sparta.fintech.ledger.domain.LiJournalEntryLine;
import com.sparta.fintech.ledger.domain.LmJournalEntry;
import com.sparta.fintech.ledger.domain.LcLedgerAccount;
import com.sparta.fintech.ledger.domain.DmTransaction;
import com.sparta.fintech.ledger.domain.TransactionType;
import com.sparta.fintech.ledger.domain.StoredMoney;
import com.sparta.fintech.ledger.domain.LedgerPostingRules;
import com.sparta.fintech.ledger.repository.DmAccountRepository;
import com.sparta.fintech.ledger.repository.DiAccountTransactionRepository;
import com.sparta.fintech.ledger.repository.LiAccountingLedgerRepository;
import com.sparta.fintech.ledger.repository.LiJournalEntryLineRepository;
import com.sparta.fintech.ledger.repository.LmJournalEntryRepository;
import com.sparta.fintech.ledger.repository.LcLedgerAccountRepository;
import com.sparta.fintech.ledger.repository.DmTransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 1 / 6-1] 거래와 계좌거래, 전표 및 회계원장을 함께 저장하고 완료 상태와 결과를 연결하세요. */
public class LedgerPostingService {

    private static final DateTimeFormatter VOUCHER_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    private final Clock clock;
    private final DmTransactionRepository transactionRepository;
    private final DmAccountRepository accountRepository;
    private final DiAccountTransactionRepository accountTransactionRepository;
    private final LcLedgerAccountRepository ledgerAccountRepository;
    private final LmJournalEntryRepository journalEntryRepository;
    private final LiJournalEntryLineRepository journalEntryLineRepository;
    private final LiAccountingLedgerRepository accountingLedgerRepository;

    public LedgerPostingService(
        DmTransactionRepository transactionRepository,
        DmAccountRepository accountRepository,
        DiAccountTransactionRepository accountTransactionRepository,
        LcLedgerAccountRepository ledgerAccountRepository,
        LmJournalEntryRepository journalEntryRepository,
        LiJournalEntryLineRepository journalEntryLineRepository,
        LiAccountingLedgerRepository accountingLedgerRepository,
        Clock clock
    ) {
        this.clock = clock;
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
        this.ledgerAccountRepository = ledgerAccountRepository;
        this.journalEntryRepository = journalEntryRepository;
        this.journalEntryLineRepository = journalEntryLineRepository;
        this.accountingLedgerRepository = accountingLedgerRepository;
    }

    public LedgerPostingResult post(LedgerPostingCommand command) {
        // TODO [특강 1 / 6-1] 거래와 계좌거래, 전표 및 회계원장을 함께 저장하고 완료 상태와 결과를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 6-1] 거래와 계좌거래, 전표 및 회계원장을 함께 저장하고 완료 상태와 결과를 연결하세요.");
    }

    public LedgerPostingResult postTransfer(LedgerPostingCommand command) {
        // TODO [특강 2 / 6-3] API 송금의 거래 출처를 구분하며 기존 원장 저장과 동일한 검증을 적용하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-3] API 송금의 거래 출처를 구분하며 기존 원장 저장과 동일한 검증을 적용하세요.");
    }

    private LedgerPostingResult post(LedgerPostingCommand command, boolean apiTransfer) {
        // TODO [특강 1 / 6-1] 거래와 계좌거래, 전표 및 회계원장을 함께 저장하고 완료 상태와 결과를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 6-1] 거래와 계좌거래, 전표 및 회계원장을 함께 저장하고 완료 상태와 결과를 연결하세요.");
    }

    private void validate(LedgerPostingCommand command) {
        // TODO [특강 1 / 4-2] 필수 식별값, 양수 금액과 저장 범위, 계좌거래 및 유형별 분개 구성을 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 필수 식별값, 양수 금액과 저장 범위, 계좌거래 및 유형별 분개 구성을 검사하세요.");
    }

    private String originalTid(LedgerPostingCommand command) {
        // TODO [특강 1 / 5-1] 명시한 OID를 유지하고 없을 때 같은 거래를 추적할 원거래 TID를 정하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 5-1] 명시한 OID를 유지하고 없을 때 같은 거래를 추적할 원거래 TID를 정하세요.");
    }

    private void validateAmount(BigDecimal amount, String name) {
        // TODO [특강 1 / 4-2] StoredMoney로 양수와 저장 범위를 확인하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] StoredMoney로 양수와 저장 범위를 확인하세요.");
    }

    private BigDecimal sum(LedgerPostingCommand command, DebitCreditType debitCreditType) {
        // TODO [특강 1 / 4-2] 요청한 차대 방향에 속한 전표 금액의 합계를 구하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 4-2] 요청한 차대 방향에 속한 전표 금액의 합계를 구하세요.");
    }

    private DmAccount getAccount(Long accountId) {
        // TODO [특강 1 / 6-1] 고객 계좌 ID로 계좌를 조회하고 없는 참조는 저장 전에 거절하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 6-1] 고객 계좌 ID로 계좌를 조회하고 없는 참조는 저장 전에 거절하세요.");
    }

    private void requireText(String value, String name) {
        // TODO [특강 1 / 6-1] 필수 추적 ID와 통화의 null 및 빈 문자열을 거절하세요.
        throw new UnsupportedOperationException("TODO [특강 1 / 6-1] 필수 추적 ID와 통화의 null 및 빈 문자열을 거절하세요.");
    }

    private String generateVoucherNo(LocalDate businessDate) {
        String date = businessDate.format(VOUCHER_DATE_FORMAT);
        String suffix = UUID.randomUUID().toString().replace("-", "").substring(0, 12).toUpperCase();
        return "JV-" + date + "-" + suffix;
    }
}
