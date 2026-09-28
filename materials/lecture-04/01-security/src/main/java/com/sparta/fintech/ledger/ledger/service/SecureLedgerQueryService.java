package com.sparta.fintech.ledger.ledger.service;

import com.sparta.fintech.ledger.domain.AuditActionType;
import com.sparta.fintech.ledger.domain.DmAccount;
import com.sparta.fintech.ledger.domain.LiAccountingLedger;
import com.sparta.fintech.ledger.repository.DmAccountRepository;
import com.sparta.fintech.ledger.repository.LiAccountingLedgerRepository;
import com.sparta.fintech.ledger.security.service.AuditLogService;
import com.sparta.fintech.ledger.security.service.CurrentUser;
import com.sparta.fintech.ledger.security.service.CurrentUserService;
import com.sparta.fintech.ledger.security.service.MaskingService;
import java.util.Comparator;
import java.util.List;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 민감정보를 마스킹하세요. */
public class SecureLedgerQueryService {

    private final DmAccountRepository accountRepository;
    private final LiAccountingLedgerRepository accountingLedgerRepository;
    private final CurrentUserService currentUserService;
    private final MaskingService maskingService;
    private final AuditLogService auditLogService;

    public SecureLedgerQueryService(
        DmAccountRepository accountRepository,
        LiAccountingLedgerRepository accountingLedgerRepository,
        CurrentUserService currentUserService,
        MaskingService maskingService,
        AuditLogService auditLogService
    ) {
        this.accountRepository = accountRepository;
        this.accountingLedgerRepository = accountingLedgerRepository;
        this.currentUserService = currentUserService;
        this.maskingService = maskingService;
        this.auditLogService = auditLogService;
    }

    public List<MaskedLedgerItem> getMyLedgerItems(String accountNo) {
        // TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 민감정보를 마스킹하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 민감정보를 마스킹하세요.");
    }

    private void validateOwner(CurrentUser currentUser, DmAccount account) {
        // TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 민감정보를 마스킹하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 민감정보를 마스킹하세요.");
    }

    private MaskedLedgerItem mask(LiAccountingLedger ledger) {
        // TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 민감정보를 마스킹하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 민감정보를 마스킹하세요.");
    }
}
