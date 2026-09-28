package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.DmAccountBalance;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmAccountBalanceRepository extends JpaRepository<DmAccountBalance, Long> {

    Optional<DmAccountBalance> findByAccountAccountId(Long accountId);
}
