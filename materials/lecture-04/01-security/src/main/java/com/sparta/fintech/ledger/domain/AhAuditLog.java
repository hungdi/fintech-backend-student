package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import java.time.Instant;

/** TODO [특강 4 / 5-2] 감사 이력의 주체와 결과, 금융 추적 ID 및 요청 추적 ID를 매핑하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class AhAuditLog extends BaseTimeEntity {

    private Long auditLogId;

    private Long authUserId;

    private Long customerId;

    private String username;

    private AuditActionType actionType;

    private AuditResultType resultType;

    private String resourceType;

    private String resourceId;

    private String tid;

    private String gid;

    private String oid;

    private String requestTraceId;

    private String message;

    private Instant occurredAt;

    protected AhAuditLog() {
    }

    public AhAuditLog(
        Long authUserId,
        Long customerId,
        String username,
        AuditActionType actionType,
        AuditResultType resultType,
        String resourceType,
        String resourceId,
        String tid,
        String gid,
        String oid,
        String requestTraceId,
        String message
    ) {
        this(authUserId, customerId, username, actionType, resultType, resourceType, resourceId, tid, gid, oid, requestTraceId, message, Instant.now());
    }

    public AhAuditLog(
        Long authUserId,
        Long customerId,
        String username,
        AuditActionType actionType,
        AuditResultType resultType,
        String resourceType,
        String resourceId,
        String tid,
        String gid,
        String oid,
        String requestTraceId,
        String message,
        Instant now
    ) {
        this.authUserId = authUserId;
        this.customerId = customerId;
        this.username = username;
        this.actionType = actionType;
        this.resultType = resultType;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.tid = tid;
        this.gid = gid;
        this.oid = oid;
        this.requestTraceId = requestTraceId;
        this.message = message;
        this.occurredAt = now;
    }

    public Long getAuditLogId() {
        return auditLogId;
    }

    public Long getAuthUserId() {
        return authUserId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getUsername() {
        return username;
    }

    public AuditActionType getActionType() {
        return actionType;
    }

    public AuditResultType getResultType() {
        return resultType;
    }

    public String getResourceType() {
        return resourceType;
    }

    public String getResourceId() {
        return resourceId;
    }

    public String getTid() {
        return tid;
    }

    public String getGid() {
        return gid;
    }

    public String getOid() {
        return oid;
    }

    public String getRequestTraceId() {
        return requestTraceId;
    }

    public String getMessage() {
        return message;
    }

    public Instant getOccurredAt() {
        return occurredAt;
    }
}
