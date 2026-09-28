package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.DmTransferOrder;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DmTransferOrderRepository extends JpaRepository<DmTransferOrder, Long> {

    Optional<DmTransferOrder> findByIdempotencyKey(String idempotencyKey);

    List<DmTransferOrder> findByGid(String gid);
}
