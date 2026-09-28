package com.sparta.fintech.ledger.transfer.service;

import com.sparta.fintech.ledger.domain.AccountStatus;
import com.sparta.fintech.ledger.domain.AuditActionType;
import com.sparta.fintech.ledger.domain.CustomerStatus;
import com.sparta.fintech.ledger.domain.DmAccount;
import com.sparta.fintech.ledger.domain.DmAccountLimit;
import com.sparta.fintech.ledger.domain.LimitPeriod;
import com.sparta.fintech.ledger.domain.LimitType;
import com.sparta.fintech.ledger.repository.DmAccountLimitRepository;
import com.sparta.fintech.ledger.repository.DmTransferOrderRepository;
import com.sparta.fintech.ledger.security.service.AuditLogService;
import com.sparta.fintech.ledger.security.service.CurrentUser;
import com.sparta.fintech.ledger.security.service.CurrentUserService;
import com.sparta.fintech.ledger.security.service.MaskingService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Comparator;
import java.util.List;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

@Service
/** TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요. */
public class SecureTransferService {

    private final TransferService transferService;
    private final DmAccountLimitRepository accountLimitRepository;
    private final DmTransferOrderRepository transferOrderRepository;
    private final CurrentUserService currentUserService;
    private final MaskingService maskingService;
    private final AuditLogService auditLogService;

    public SecureTransferService(
        TransferService transferService,
        DmAccountLimitRepository accountLimitRepository,
        DmTransferOrderRepository transferOrderRepository,
        CurrentUserService currentUserService,
        MaskingService maskingService,
        AuditLogService auditLogService
    ) {
        this.transferService = transferService;
        this.accountLimitRepository = accountLimitRepository;
        this.transferOrderRepository = transferOrderRepository;
        this.currentUserService = currentUserService;
        this.maskingService = maskingService;
        this.auditLogService = auditLogService;
    }

    public TransferResult transfer(TransferCommand command) {
        // TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.");
    }

    private void validateOwner(CurrentUser currentUser, DmAccount withdrawalAccount) {
        // TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.");
    }

    private void validateActive(DmAccount account) {
        // TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.");
    }

    private void validateDailyTransferLimit(
        DmAccount withdrawalAccount, BigDecimal amount, String currencyCode, LocalDate today
    ) {
        // TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.");
    }

    private boolean isEffective(DmAccountLimit limit, LocalDate today) {
        // TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-2] 본인 출금 계좌와 일일 한도를 기존 송금 정책에 연결하고 재시도와 동시성을 지키세요.");
    }
}
