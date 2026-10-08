package com.sparta.fintech.ledger.support;

/** 내부 송금 오더와 원장 샘플을 공유하고 외부 비교에 필요한 기관 코드를 지정합니다. */
public abstract class ReconciliationTestSupport extends InternalReconciliationTestSupport {
    @Override
    protected String externalInstitutionCode() {
        return "OTHER-BANK";
    }
}
