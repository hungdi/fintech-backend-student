package com.sparta.fintech.ledger.reconciliation.web;

import com.sparta.fintech.ledger.reconciliation.service.OperationsReconciliationQueryService;
import com.sparta.fintech.ledger.reconciliation.service.OperationsReconciliationQueryService.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/operations/reconciliations")
/** TODO [특강 4 / 8-5] 운영 역할에만 대사 조회를 허용하고 결과 Service를 연결하세요. */
public class OperationsReconciliationController {
    private final OperationsReconciliationQueryService queries;
    public OperationsReconciliationController(OperationsReconciliationQueryService queries) { this.queries = queries; }

    @GetMapping("/{runId}")
    public RunResponse run(@PathVariable Long runId) {
        // TODO [특강 4 / 8-5] 운영 역할에만 대사 조회를 허용하고 결과 Service를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 8-5] 운영 역할에만 대사 조회를 허용하고 결과 Service를 연결하세요.");
    }

    @GetMapping("/{runId}/results")
    public ResultsResponse results(@PathVariable Long runId,
        @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        // TODO [특강 4 / 8-5] 운영 역할에만 대사 조회를 허용하고 결과 Service를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 8-5] 운영 역할에만 대사 조회를 허용하고 결과 Service를 연결하세요.");
    }
}
