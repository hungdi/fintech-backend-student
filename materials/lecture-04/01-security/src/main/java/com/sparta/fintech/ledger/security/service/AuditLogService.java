package com.sparta.fintech.ledger.security.service;

import com.sparta.fintech.ledger.domain.*;
import com.sparta.fintech.ledger.transfer.service.IdempotencyKeyConflictException;
import com.sparta.fintech.ledger.transfer.service.TransferInProgressException;
import java.time.Clock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
/** TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요. */
public class AuditLogService {
    private static final Logger log = LoggerFactory.getLogger(AuditLogService.class);
    private final AuditLogWriter writer;
    private final Clock clock;

    public AuditLogService(AuditLogWriter writer, Clock clock) { this.writer = writer; this.clock = clock; }

    public AhAuditLog success(CurrentUser user, AuditActionType action, String type, String id,
                              String tid, String gid, String oid, String message) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }
    public AhAuditLog denied(CurrentUser user, AuditActionType action, String type, String id,
                             String tid, String gid, String oid, String message) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }
    public AhAuditLog anonymousDenied(AuditActionType action, String type, String id, String message) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }
    public AhAuditLog failure(CurrentUser user, AuditActionType action, String type, String id, RuntimeException failure) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }
    public AhAuditLog requestFailure(String uri, boolean systemError) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }
    public AhAuditLog authenticationFailure(String uri, boolean systemError) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }

    private CurrentUser currentActor() {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }

    private AhAuditLog save(CurrentUser user, AuditActionType action, AuditResultType result, String type, String id,
                            String tid, String gid, String oid, String message) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }

    public static String safeText(String text, int maxLength) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }

    private static String safeResource(String value) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }
    private static String shortText(String text, int limit) {
        // TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 감사 주체와 결과를 연결하고 입력 문자열을 제한해 중복 기록과 개인정보 노출을 막으세요.");
    }
}
