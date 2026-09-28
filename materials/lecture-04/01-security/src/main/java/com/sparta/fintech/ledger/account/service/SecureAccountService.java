package com.sparta.fintech.ledger.account.service;

import com.sparta.fintech.ledger.domain.AuditActionType;
import com.sparta.fintech.ledger.domain.DmAccount;
import com.sparta.fintech.ledger.domain.DmAccountBalance;
import com.sparta.fintech.ledger.repository.DmAccountBalanceRepository;
import com.sparta.fintech.ledger.repository.DmAccountRepository;
import com.sparta.fintech.ledger.security.service.AuditLogService;
import com.sparta.fintech.ledger.security.service.CurrentUser;
import com.sparta.fintech.ledger.security.service.CurrentUserService;
import com.sparta.fintech.ledger.security.service.MaskingService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 4 / 3-1] 조회 계좌의 소유권과 상태를 확인하고 개인정보를 보호한 응답을 구성하세요. */
public class SecureAccountService {

    private final DmAccountRepository accountRepository;
    private final DmAccountBalanceRepository accountBalanceRepository;
    private final CurrentUserService currentUserService;
    private final MaskingService maskingService;
    private final AuditLogService auditLogService;

    public SecureAccountService(
        DmAccountRepository accountRepository,
        DmAccountBalanceRepository accountBalanceRepository,
        CurrentUserService currentUserService,
        MaskingService maskingService,
        AuditLogService auditLogService
    ) {
        this.accountRepository = accountRepository;
        this.accountBalanceRepository = accountBalanceRepository;
        this.currentUserService = currentUserService;
        this.maskingService = maskingService;
        this.auditLogService = auditLogService;
    }

    public AccountSummary getMyAccount(String accountNo) {
        // TODO [특강 4 / 3-1] 조회 계좌의 소유권과 상태를 확인하고 개인정보를 보호한 응답을 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-1] 조회 계좌의 소유권과 상태를 확인하고 개인정보를 보호한 응답을 구성하세요.");
    }

    private void validateOwner(CurrentUser currentUser, DmAccount account) {
        // TODO [특강 4 / 3-1] 조회 계좌의 소유권과 상태를 확인하고 개인정보를 보호한 응답을 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-1] 조회 계좌의 소유권과 상태를 확인하고 개인정보를 보호한 응답을 구성하세요.");
    }
}
