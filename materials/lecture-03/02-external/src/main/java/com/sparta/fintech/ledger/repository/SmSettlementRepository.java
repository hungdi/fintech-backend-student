package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.SmSettlement;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmSettlementRepository extends JpaRepository<SmSettlement, Long> {

    List<SmSettlement> findByBaseDate(LocalDate baseDate);
}
