package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.DiAccountTransaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiAccountTransactionRepository extends JpaRepository<DiAccountTransaction, Long> {

    List<DiAccountTransaction> findByTid(String tid);

    List<DiAccountTransaction> findByGid(String gid);
}
