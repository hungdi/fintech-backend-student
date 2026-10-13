package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmExternalReconciliationRepository extends JpaRepository<SmExternalReconciliation, Long> {
    List<SmExternalReconciliation> findByBaseDate(LocalDate date);
    Optional<SmExternalReconciliation> findFirstByBaseDateAndExternalInstitutionCodeAndCurrencyCodeAndSourceOrderByExternalReconciliationIdDesc(
        LocalDate date, String institution, String currency, ExternalReconciliationSource source);
}
