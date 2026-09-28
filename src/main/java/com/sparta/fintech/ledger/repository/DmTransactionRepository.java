package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.DmTransaction;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmTransactionRepository extends JpaRepository<DmTransaction, Long> {

    boolean existsByTid(String tid);

    Optional<DmTransaction> findByTid(String tid);

    List<DmTransaction> findByGid(String gid);
}
