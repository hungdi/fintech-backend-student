package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.LiAccountingLedger;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiAccountingLedgerRepository extends JpaRepository<LiAccountingLedger, Long> {

    List<LiAccountingLedger> findByGid(String gid);
}
