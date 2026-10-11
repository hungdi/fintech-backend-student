package com.sparta.fintech.ledger.support;

import com.sparta.fintech.ledger.domain.SiSettlementSampleObligation;
import com.sparta.fintech.ledger.reconciliation.service.SettlementService;
import com.sparta.fintech.ledger.repository.SiSettlementSampleObligationRepository;
import com.sparta.fintech.ledger.repository.SmSettlementRepository;
import org.springframework.beans.factory.annotation.Autowired;

/** 정산 표본은 실제 고객 송금과 별도로 적재합니다. 업무 집계·확정은 제공 코드에 포함하지 않습니다. */
public abstract class SettlementTestSupport extends ExternalReconciliationTestSupport {
    @Autowired protected SettlementService settlementService;
    @Autowired protected SmSettlementRepository settlementRepository;
    @Autowired protected SiSettlementSampleObligationRepository sampleObligationRepository;

    protected void createSampleObligations(SettlementSamples.Sample sample) {
        for (var row : sample.internalObligations()) {
            sampleObligationRepository.save(new SiSettlementSampleObligation(
                sample.baseDate(), sample.externalInstitutionCode(), sample.currencyCode(),
                row.tid(), row.gid(), row.direction(), row.amount()));
        }
    }
}
