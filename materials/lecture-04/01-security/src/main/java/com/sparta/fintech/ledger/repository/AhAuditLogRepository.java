package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.AhAuditLog;
import com.sparta.fintech.ledger.domain.AuditActionType;
import com.sparta.fintech.ledger.domain.AuditResultType;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AhAuditLogRepository extends JpaRepository<AhAuditLog, Long> {

    List<AhAuditLog> findByActionTypeAndResultType(AuditActionType actionType, AuditResultType resultType);

    List<AhAuditLog> findByGid(String gid);
}
