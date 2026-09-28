package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.AhTransferCompletion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AhTransferCompletionRepository extends JpaRepository<AhTransferCompletion, Long> {
    long countByTransferOrderId(Long transferOrderId);
}
