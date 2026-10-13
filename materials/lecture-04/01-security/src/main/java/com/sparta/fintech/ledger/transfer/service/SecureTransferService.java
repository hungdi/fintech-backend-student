package com.sparta.fintech.ledger.transfer.service;

import com.sparta.fintech.ledger.domain.AccountStatus;
import com.sparta.fintech.ledger.domain.AuditActionType;
import com.sparta.fintech.ledger.domain.CustomerStatus;
import com.sparta.fintech.ledger.domain.DmAccount;
import com.sparta.fintech.ledger.security.service.AuditLogService;
import com.sparta.fintech.ledger.security.service.CurrentUser;
import com.sparta.fintech.ledger.security.service.CurrentUserService;
import com.sparta.fintech.ledger.security.service.MaskingService;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
/** TODO [특강 4 / 3-2] 소유권은 모든 요청에, 계좌·고객 상태는 새 송금에만 검증하고 기존 송금 정책과 감사 기록을 연결하세요. */
public class SecureTransferService {

    private final TransferService transferService;
    private final CurrentUserService currentUserService;
    private final MaskingService maskingService;
    private final AuditLogService auditLogService;

    public SecureTransferService(
        TransferService transferService,
        CurrentUserService currentUserService,
        MaskingService maskingService,
        AuditLogService auditLogService
    ) {
        this.transferService = transferService;
        this.currentUserService = currentUserService;
        this.maskingService = maskingService;
        this.auditLogService = auditLogService;
    }

    public TransferResult transfer(TransferCommand command) {
        // TODO [특강 4 / 3-2] 소유권은 모든 요청에, 계좌·고객 상태는 새 송금에만 검증하고 기존 송금 정책과 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-2] 소유권은 모든 요청에, 계좌·고객 상태는 새 송금에만 검증하고 기존 송금 정책과 감사 기록을 연결하세요.");
    }

    private void validateOwner(CurrentUser currentUser, DmAccount withdrawalAccount) {
        // TODO [특강 4 / 3-2] 출금 계좌의 고객 ID와 로그인 고객 ID를 새 송금과 재요청 모두에서 비교하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-2] 출금 계좌의 고객 ID와 로그인 고객 ID를 새 송금과 재요청 모두에서 비교하세요.");
    }

    private void validateActive(DmAccount account) {
        // TODO [특강 4 / 3-3] 출금 계좌와 소유 고객의 ACTIVE 상태를 새 송금에서만 검사하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-3] 출금 계좌와 소유 고객의 ACTIVE 상태를 새 송금에서만 검사하세요.");
    }

}
