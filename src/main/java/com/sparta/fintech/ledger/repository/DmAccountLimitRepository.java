package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.DmAccountLimit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmAccountLimitRepository extends JpaRepository<DmAccountLimit, Long> {
}
