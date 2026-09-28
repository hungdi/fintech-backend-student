package com.sparta.fintech.ledger.support;

import com.sparta.fintech.ledger.domain.*;
import com.sparta.fintech.ledger.repository.*;
import com.sparta.fintech.ledger.service.*;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 특강 4 / 8-4: 앞 강의에서 완성한 잔액 변경과 원장 Service를 호출하는 초기 입금 자료입니다.
 * 빈 테스트 DB에 고객예수금 계정과 계좌를 준비한 뒤 트랜잭션 안에서 한 번 호출하세요.
 * 고정 TID와 현금 계정코드를 사용하므로 반복 호출 전에는 테스트 DB를 초기화합니다.
 */
public final class FinancialTestData {
    private FinancialTestData() {}
    public static void openingDeposit(Long accountId, BigDecimal amount, DmAccountBalanceRepository balances,
        LcLedgerAccountRepository codes, LedgerPostingService ledger) {
        if (!TransactionSynchronizationManager.isActualTransactionActive()) throw new IllegalStateException("트랜잭션이 필요합니다.");
        codes.save(new LcLedgerAccount("100101", "현금", DebitCreditType.DEBIT));
        var balance = balances.findByAccountIdForUpdate(accountId).orElseThrow();
        balance.increase(amount);
        ledger.post(new LedgerPostingCommand("T-OPENING", "G-OPENING", null, TransactionType.DEPOSIT,
            amount, "KRW", "초기 입금", List.of(new LedgerPostingCommand.AccountPosting(accountId,
                DebitCreditType.CREDIT, amount, "초기 입금", balance.getLedgerBalance())),
            List.of(new LedgerPostingCommand.JournalPosting("100101", null, DebitCreditType.DEBIT, amount, "현금 입금"),
                new LedgerPostingCommand.JournalPosting("210101", accountId, DebitCreditType.CREDIT, amount, "고객예수금 증가"))));
    }
}
