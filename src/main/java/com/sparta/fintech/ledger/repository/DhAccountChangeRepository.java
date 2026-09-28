package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.DhAccountChange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DhAccountChangeRepository extends JpaRepository<DhAccountChange, Long> {
}
