package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.ReconciliationResultStatus;
import com.sparta.fintech.ledger.domain.ReconciliationTargetType;
import com.sparta.fintech.ledger.domain.SiReconciliationResult;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiReconciliationResultRepository extends JpaRepository<SiReconciliationResult, Long> {

    List<SiReconciliationResult> findByReconciliationRunReconciliationRunId(Long reconciliationRunId);

    List<SiReconciliationResult> findByBaseDate(LocalDate baseDate);

    List<SiReconciliationResult> findByTargetTypeAndResultStatus(
        ReconciliationTargetType targetType,
        ReconciliationResultStatus resultStatus
    );
}
