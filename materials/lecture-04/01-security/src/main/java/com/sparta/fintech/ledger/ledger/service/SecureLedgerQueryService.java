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
/** TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 계좌번호를 마스킹한 7개 필드를 응답하세요. 고객 원장 응답에 TID/GID/OID를 포함하지 않습니다. */
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
        // TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 계좌번호를 마스킹한 7개 필드를 응답하세요. 고객 원장 응답에 TID/GID/OID를 포함하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 계좌번호를 마스킹한 7개 필드를 응답하세요. 고객 원장 응답에 TID/GID/OID를 포함하지 않습니다.");
    }

    private void validateOwner(CurrentUser currentUser, DmAccount account) {
        // TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 계좌번호를 마스킹한 7개 필드를 응답하세요. 고객 원장 응답에 TID/GID/OID를 포함하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 계좌번호를 마스킹한 7개 필드를 응답하세요. 고객 원장 응답에 TID/GID/OID를 포함하지 않습니다.");
    }

    private MaskedLedgerItem mask(LiAccountingLedger ledger) {
        // TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 계좌번호를 마스킹한 7개 필드를 응답하세요. 고객 원장 응답에 TID/GID/OID를 포함하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-3] 원장 조회 계좌의 소유권을 확인하고 계좌번호를 마스킹한 7개 필드를 응답하세요. 고객 원장 응답에 TID/GID/OID를 포함하지 않습니다.");
    }
}
