package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.SiExternalReconciliationDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiExternalReconciliationDetailRepository extends JpaRepository<SiExternalReconciliationDetail, Long> {

    List<SiExternalReconciliationDetail> findByExternalReconciliationExternalReconciliationId(Long externalReconciliationId);
}
