package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.DmAccount;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmAccountRepository extends JpaRepository<DmAccount, Long> {

    Optional<DmAccount> findByAccountNo(String accountNo);
}
