package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.SiSettlementSampleObligation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiSettlementSampleObligationRepository extends JpaRepository<SiSettlementSampleObligation, Long> {
    List<SiSettlementSampleObligation> findByBaseDateAndExternalInstitutionCodeAndCurrencyCodeOrderByTargetTid(
        LocalDate date, String institution, String currency);
}
