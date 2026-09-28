package com.sparta.fintech.ledger.security.service;

import com.sparta.fintech.ledger.domain.AhAuditLog;
import com.sparta.fintech.ledger.repository.AhAuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 4 / 5-3-1] 송금 실패와 독립적으로 거절 감사 기록을 보존할 트랜잭션 경계를 적용하세요. */
public class AuditLogWriter {
    private final AhAuditLogRepository repository;
    public AuditLogWriter(AhAuditLogRepository repository) { this.repository = repository; }

    public AhAuditLog save(AhAuditLog log) {
        // TODO [특강 4 / 5-3-1] 송금 실패와 독립적으로 거절 감사 기록을 보존할 트랜잭션 경계를 적용하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-3-1] 송금 실패와 독립적으로 거절 감사 기록을 보존할 트랜잭션 경계를 적용하세요.");
    }
}
