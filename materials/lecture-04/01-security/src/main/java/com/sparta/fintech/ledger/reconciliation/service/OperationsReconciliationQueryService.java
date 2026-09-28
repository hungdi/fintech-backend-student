package com.sparta.fintech.ledger.reconciliation.service;

import com.sparta.fintech.ledger.domain.*;
import com.sparta.fintech.ledger.repository.*;
import com.sparta.fintech.ledger.security.service.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service

/** TODO [특강 4 / 8-5] 운영 역할로 대사 실행과 페이지별 결과를 조회하고 응답과 감사 기록을 구성하세요. */
public class OperationsReconciliationQueryService {
    private final SsReconciliationRunRepository runs;
    private final SiReconciliationResultRepository results;
    private final CurrentUserService users;
    private final AuditLogService audit;

    public OperationsReconciliationQueryService(SsReconciliationRunRepository runs,
        SiReconciliationResultRepository results, CurrentUserService users, AuditLogService audit) {
        this.runs = runs; this.results = results; this.users = users; this.audit = audit;
    }
    public RunResponse run(Long runId) {
        // TODO [특강 4 / 8-5] 운영 역할로 대사 실행과 페이지별 결과를 조회하고 응답과 감사 기록을 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 8-5] 운영 역할로 대사 실행과 페이지별 결과를 조회하고 응답과 감사 기록을 구성하세요.");
    }
    public ResultsResponse results(Long runId, int page, int size) {
        // TODO [특강 4 / 8-5] 운영 역할로 대사 실행과 페이지별 결과를 조회하고 응답과 감사 기록을 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 8-5] 운영 역할로 대사 실행과 페이지별 결과를 조회하고 응답과 감사 기록을 구성하세요.");
    }
    private SsReconciliationRun requireRun(Long id) {
        // TODO [특강 4 / 8-5] 운영 역할로 대사 실행과 페이지별 결과를 조회하고 응답과 감사 기록을 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 8-5] 운영 역할로 대사 실행과 페이지별 결과를 조회하고 응답과 감사 기록을 구성하세요.");
    }
    public record RunResponse(Long runId, LocalDate baseDate, ReconciliationRunStatus status,
        Instant startedAt, Instant completedAt, int totalCount, int mismatchCount) {}
    public record ResultsResponse(Long runId, int page, int size, long totalElements, int totalPages, List<ResultResponse> content) {}
    public record ResultResponse(Long resultId, ReconciliationTargetType targetType, String tid, String gid,
        Long accountId, BigDecimal expectedAmount, BigDecimal actualAmount, BigDecimal differenceAmount,
        ReconciliationResultStatus status, String reason) {}
}
