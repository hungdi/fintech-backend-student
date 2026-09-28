package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.LcLedgerAccount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LcLedgerAccountRepository extends JpaRepository<LcLedgerAccount, Long> {

    Optional<LcLedgerAccount> findByAccountCode(String accountCode);
}
