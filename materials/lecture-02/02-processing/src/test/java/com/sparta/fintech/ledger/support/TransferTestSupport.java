package com.sparta.fintech.ledger.support;

import com.sparta.fintech.ledger.domain.*;
import com.sparta.fintech.ledger.repository.*;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDate;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/** 상속한 테스트에는 클래스 전체의 트랜잭션을 붙이지 않습니다. Service 커밋 후 다시 조회하세요. */
@SpringBootTest
@ActiveProfiles("test")
public abstract class TransferTestSupport {
    @Autowired protected CmCustomerRepository customers;
    @Autowired protected DmAccountRepository accounts;
    @Autowired protected DmAccountBalanceRepository balances;
    @Autowired protected DmAccountLimitRepository limits;
    @Autowired protected LcLedgerAccountRepository codes;
    @Autowired protected DataSource dataSource;
    @Autowired protected Clock clock;

    @BeforeEach
    void resetPracticeDatabase() throws Exception { TestDatabaseReset.clear(dataSource); }

    protected Accounts createTransferAccounts() {
        return createTransferAccounts("1000000", "100000", "1000000");
    }

    /** 각 테스트에서 한 번 호출합니다. 송금 처리와 초기 원장 저장은 수행하지 않습니다. */
    protected Accounts createTransferAccounts(String withdrawalBalance, String depositBalance, String perTransferLimit) {
        BigDecimal withdrawalAmount = new BigDecimal(withdrawalBalance);
        BigDecimal depositAmount = new BigDecimal(depositBalance);
        BigDecimal limitAmount = new BigDecimal(perTransferLimit);
        var fromCustomer = customers.save(new CmCustomer("CUST-17", "박개발", "from@example.com", "010-1111-1111"));
        var toCustomer = customers.save(new CmCustomer("CUST-18", "강동원", "to@example.com", "010-2222-2222"));
        var from = accounts.save(new DmAccount(fromCustomer, "110-001", "월세 출금 계좌", "KRW"));
        var to = accounts.save(new DmAccount(toCustomer, "110-002", "월세 입금 계좌", "KRW"));
        balances.save(new DmAccountBalance(from, withdrawalAmount, withdrawalAmount));
        balances.save(new DmAccountBalance(to, depositAmount, depositAmount));
        limits.save(new DmAccountLimit(fromCustomer, from, LimitType.TRANSFER, LimitPeriod.PER_TRANSACTION,
            limitAmount, "KRW", LocalDate.now(clock).minusDays(1)));
        codes.save(new LcLedgerAccount("210101", "고객예수금", DebitCreditType.CREDIT));
        return new Accounts(from.getAccountId(), to.getAccountId(), from.getAccountNo(), to.getAccountNo());
    }
    public record Accounts(Long fromId, Long toId, String fromNo, String toNo) {}
}
