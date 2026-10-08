package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.domain.DmAccountBalance;
import com.sparta.fintech.ledger.domain.DmAccount;
import com.sparta.fintech.ledger.domain.CmCustomer;
import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("exercise")
class TransferExerciseTest {
    @Test
    void withdrawalPreservesHeldAmount() {
        var customer = new CmCustomer("CUST-17", "박개발", "student@example.com", "010-1111-1111");
        var account = new DmAccount(customer, "110-001", "출금 계좌", "KRW");
        var balance = new DmAccountBalance(account, new BigDecimal("100000"), new BigDecimal("80000"));
        balance.decrease(new BigDecimal("30000"));
        assertThat(balance.getLedgerBalance()).isEqualByComparingTo("70000");
        assertThat(balance.getAvailableBalance()).isEqualByComparingTo("50000");
    }
}
