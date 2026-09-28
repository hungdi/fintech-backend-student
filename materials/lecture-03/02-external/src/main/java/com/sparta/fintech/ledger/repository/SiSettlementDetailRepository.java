package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.SiSettlementDetail;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiSettlementDetailRepository extends JpaRepository<SiSettlementDetail, Long> {

    List<SiSettlementDetail> findBySettlementSettlementId(Long settlementId);
}
