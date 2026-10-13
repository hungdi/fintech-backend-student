package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.SmAccountDailyClosing;
import jakarta.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SmAccountDailyClosingRepository extends JpaRepository<SmAccountDailyClosing, Long> {

    Optional<SmAccountDailyClosing> findByAccountAccountIdAndBaseDate(Long accountId, LocalDate baseDate);

    boolean existsByAccountAccountId(Long accountId);

    // TODO [특강 3 / 2-5-2] 해당 계좌의 기준일 이후 행을 날짜 순으로 조회하고 쓰기 잠금을 적용하세요.
    List<SmAccountDailyClosing> findByAccountAccountIdAndBaseDateGreaterThanEqual(
        @Param("accountId") Long accountId, @Param("baseDate") LocalDate baseDate);

    List<SmAccountDailyClosing> findByBaseDate(LocalDate baseDate);

    // TODO [특강 3 / 2-5-2] @Query로 날짜를 제한하고 계좌 ID 순서로 조회하며 @Lock을 적용하세요.
    // ForUpdate는 메서드 이름만으로 잠금을 적용하지 않습니다. 계좌를 join fetch하지 마세요.
    List<SmAccountDailyClosing> findByBaseDateForUpdate(@Param("baseDate") LocalDate baseDate);
}
