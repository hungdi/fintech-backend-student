package com.sparta.fintech.ledger.support;

/** 동일 은행 송금의 외부 보고 비교 표본입니다. 기관 표시는 자금 이동 방향을 뜻하지 않습니다. */
public abstract class ExternalReconciliationTestSupport extends InternalReconciliationTestSupport {
    @org.springframework.beans.factory.annotation.Autowired
    protected com.sparta.fintech.ledger.reconciliation.service.ExternalReconciliationService externalReconciliationService;
    @org.springframework.beans.factory.annotation.Autowired
    protected com.sparta.fintech.ledger.repository.SmExternalReconciliationRepository externalReconciliationRepository;
    @org.springframework.beans.factory.annotation.Autowired
    protected com.sparta.fintech.ledger.repository.SiExternalReconciliationDetailRepository externalReconciliationDetailRepository;

    @Override
    protected String externalInstitutionCode() {
        return "OTHER-BANK";
    }
}
