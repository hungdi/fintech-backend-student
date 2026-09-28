package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.domain.DmAccountBalance;
import com.sparta.fintech.ledger.domain.DmAccount;
import com.sparta.fintech.ledger.domain.CmCustomer;
import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("exercise")
class TransferExerciseTest {
    @Test
    void withdrawalPreservesHeldAmount() {
        var customer = new CmCustomer("CUST-17", "박개발", "student@example.com", "010-1111-1111");
        var account = new DmAccount(customer, "110-001", "출금 계좌", "KRW");
        var balance = new DmAccountBalance(account, new BigDecimal("100"), new BigDecimal("80"));
        balance.decrease(new BigDecimal("30"));
        assertThat(balance.getLedgerBalance()).isEqualByComparingTo("70");
        assertThat(balance.getAvailableBalance()).isEqualByComparingTo("50");
    }

    @Test
    void rollbackRestoresBalancesAndAllLedgerRows() {
        // TODO [특강 2 / 7-3] TransferTestSupport로 DB 테스트를 준비하고 각 FailurePoint에서 예외를 주입하세요.
        // 출금, 입금, 오더, 거래, 계좌거래, 전표 상세와 회계원장이 모두 원래 상태인지 확인하세요.
        fail("TODO [특강 2 / 7-3] 원장 저장 이후 실패까지 전체 롤백을 검증하세요.");
    }

    @Test
    void retriesAndConcurrentRequestsKeepMoneyAndRequestIdentity() {
        // TODO [특강 2 / 7-4, 7-5] 같은 키의 동일 요청과 다른 금액 요청을 구분하세요.
        // 동시에 같은 키 5건을 보내 한 번만 처리되는지, 다른 키 5건은 합계가 보존되는지 확인하세요.
        fail("TODO [특강 2 / 7-4, 7-5] 멱등 재시도와 동시 요청 테스트를 작성하세요.");
    }
}
