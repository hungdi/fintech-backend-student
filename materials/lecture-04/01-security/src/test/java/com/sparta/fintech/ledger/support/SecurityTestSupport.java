package com.sparta.fintech.ledger.support;

import com.sparta.fintech.ledger.domain.*;
import com.sparta.fintech.ledger.repository.*;
import com.sparta.fintech.ledger.security.service.SensitiveDataCryptoService;
import com.sparta.fintech.ledger.reconciliation.service.DailyClosingService;
import com.sparta.fintech.ledger.reconciliation.service.ReconciliationService;
import com.sparta.fintech.ledger.service.LedgerPostingService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Clock;
import java.time.Instant;
import javax.sql.DataSource;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.crypto.password.PasswordEncoder;

/** 고정 사용자와 잔액을 준비합니다. 초기 원장은 8-4의 FinancialTestData로 별도 준비하세요. */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles({"test", "security-test"})
@Import(SecurityTestSupport.TimeConfiguration.class)
public abstract class SecurityTestSupport {
    @TestConfiguration(proxyBeanMethods = false)
    public static class TimeConfiguration {
        @Bean
        @Primary
        MutableBusinessClock securityPracticeClock() {
            // JWT 검증의 시스템 시각과 맞추고, 개별 날짜 경계 테스트에서만 시간을 진행합니다.
            return new MutableBusinessClock(Instant.now());
        }
    }

    @Autowired protected MockMvc mockMvc;
    @Autowired protected PasswordEncoder passwordEncoder;
    @Autowired protected SensitiveDataCryptoService cryptoService;
    @Autowired protected AcAuthRoleRepository authRoleRepository;
    @Autowired protected AmAuthUserRoleRepository authUserRoleRepository;
    @Autowired protected AmAuthUserRepository authUserRepository;
    @Autowired protected CmCustomerRepository customerRepository;
    @Autowired protected DmAccountRepository accountRepository;
    @Autowired protected DmAccountBalanceRepository accountBalanceRepository;
    @Autowired protected DmAccountLimitRepository accountLimitRepository;
    @Autowired protected LcLedgerAccountRepository ledgerAccountRepository;
    @Autowired protected DailyClosingService dailyClosingService;
    @Autowired protected SmAccountDailyClosingRepository dailyClosingRepository;
    @Autowired protected ReconciliationService reconciliationService;
    @Autowired protected LedgerPostingService ledgerPostingService;
    @Autowired protected MutableBusinessClock practiceClock;
    @Autowired protected Clock clock;
    @Autowired protected DataSource dataSource;
    @BeforeEach
    void resetPracticeDatabase() throws Exception {
        practiceClock.set(Instant.now());
        TestDatabaseReset.clear(dataSource);
    }
    protected SecurityFixture createSecurityFixture(String ownerBalance, String dailyLimit) {
        Instant openedAt = clock.instant();
        AcAuthRole customerRole = authRoleRepository.save(new AcAuthRole(
            "ROLE_CUSTOMER",
            "고객",
            "고객 본인 계좌 조회와 송금 권한"
        ));
        CmCustomer ownerCustomer = customerRepository.save(new CmCustomer(
            "CUST-SEC-OWNER",
            "박개발",
            "owner@example.com",
            "010-1111-1111"
        ));
        CmCustomer otherCustomer = customerRepository.save(new CmCustomer(
            "CUST-SEC-OTHER",
            "강동원",
            "other@example.com",
            "010-2222-2222"
        ));
        AmAuthUser ownerUser = authUserRepository.save(new AmAuthUser(
            ownerCustomer,
            "owner",
            passwordEncoder.encode("owner-pass"),
            cryptoService.encrypt("owner-mfa-secret")
        ));
        authUserRoleRepository.save(new AmAuthUserRole(ownerUser, customerRole));
        AmAuthUser otherUser = authUserRepository.save(new AmAuthUser(
            otherCustomer,
            "other",
            passwordEncoder.encode("other-pass"),
            cryptoService.encrypt("other-mfa-secret")
        ));
        authUserRoleRepository.save(new AmAuthUserRole(otherUser, customerRole));

        DmAccount ownerAccount = accountRepository.save(new DmAccount(
            ownerCustomer,
            "410-001",
            "보안 출금 계좌",
            "KRW",
            openedAt
        ));
        DmAccount otherAccount = accountRepository.save(new DmAccount(
            otherCustomer,
            "410-002",
            "타인 입금 계좌",
            "KRW",
            openedAt
        ));
        accountBalanceRepository.save(new DmAccountBalance(
            ownerAccount,
            new BigDecimal(ownerBalance),
            new BigDecimal(ownerBalance)
        ));
        accountBalanceRepository.save(new DmAccountBalance(
            otherAccount,
            BigDecimal.ZERO,
            BigDecimal.ZERO
        ));
        addTransferLimit(ownerCustomer, ownerAccount, LimitPeriod.PER_TRANSACTION, "1000.00");
        addTransferLimit(ownerCustomer, ownerAccount, LimitPeriod.DAILY, dailyLimit);
        addTransferLimit(otherCustomer, otherAccount, LimitPeriod.PER_TRANSACTION, "1000.00");
        addTransferLimit(otherCustomer, otherAccount, LimitPeriod.DAILY, "1000.00");
        ledgerAccountRepository.save(new LcLedgerAccount("210101", "고객예수금", DebitCreditType.CREDIT));

        return new SecurityFixture(
            ownerCustomer.getCustomerId(),
            ownerAccount.getAccountNo(),
            otherAccount.getAccountNo()
        );
    }

    private void addTransferLimit(CmCustomer customer, DmAccount account, LimitPeriod period, String amount) {
        accountLimitRepository.save(new DmAccountLimit(
            customer,
            account,
            LimitType.TRANSFER,
            period,
            new BigDecimal(amount),
            "KRW",
            LocalDate.now(clock).minusDays(1)
        ));
    }
    protected record SecurityFixture(Long ownerCustomerId, String ownerAccountNo, String otherAccountNo) {}
}
