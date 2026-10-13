package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.SmSettlement;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmSettlementRepository extends JpaRepository<SmSettlement, Long> {
    List<SmSettlement> findByBaseDate(LocalDate date);

    // TODO [특강 3 / 정산-7] JPQL와 PESSIMISTIC_WRITE 잠금을 적용하고 default 본문을 제거하세요.
    default Optional<SmSettlement> findScopeForUpdate(LocalDate date, String institution, String currency) { throw new UnsupportedOperationException("TODO [특강 3 / 정산-7] 범위 쓰기 잠금 조회를 작성하세요."); }

    // TODO [특강 3 / 정산-7] ID 쓰기 잠금 조회를 작성하세요.
    default Optional<SmSettlement> findByIdForUpdate(Long id) { throw new UnsupportedOperationException("TODO [특강 3 / 정산-7] ID 쓰기 잠금 조회를 작성하세요."); }
}
