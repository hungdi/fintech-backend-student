package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.SsReconciliationRun;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SsReconciliationRunRepository extends JpaRepository<SsReconciliationRun, Long> {

    List<SsReconciliationRun> findByBaseDate(LocalDate baseDate);
}
