package com.sparta.fintech.ledger.preparation;

import com.sparta.fintech.ledger.domain.SettlementDirection;
import com.sparta.fintech.ledger.support.SettlementSamples;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/** 제공 표본과 DTO의 연결만 검사합니다. 미구현 엔티티 매핑·집계·확정은 exercise에서 확인합니다. */
@Tag("smoke")
class SettlementMaterialsSmokeTest {
    @Test
    void providesPayableReceivableAndSignedExpectationsForCasesABC() {
        assertThat(SettlementSamples.BASE_DATE.toString()).isEqualTo("2026-09-15");
        assertThat(SettlementSamples.INSTITUTION).isEqualTo("OTHER-BANK");
        assertThat(SettlementSamples.CURRENCY).isEqualTo("KRW");
        for (var sample : java.util.List.of(SettlementSamples.caseA(), SettlementSamples.caseB(), SettlementSamples.caseC())) {
            assertThat(sample.baseDate()).isEqualTo(SettlementSamples.BASE_DATE);
            assertThat(sample.externalInstitutionCode()).isEqualTo(SettlementSamples.INSTITUTION);
            assertThat(sample.currencyCode()).isEqualTo(SettlementSamples.CURRENCY);
            assertThat(sample.internalObligations()).hasSize(2);
            assertThat(sample.internalObligations().get(0).direction()).isEqualTo(SettlementDirection.PAYABLE);
            assertThat(sample.internalObligations().get(1).direction()).isEqualTo(SettlementDirection.RECEIVABLE);
            assertThat(sample.externalItems()).hasSize(2);
        }
        assertThat(SettlementSamples.caseA().expected().net()).isEqualByComparingTo("200000");
        assertThat(SettlementSamples.caseB().expected().net()).isEqualByComparingTo("-200000");
        assertThat(SettlementSamples.caseC().expected().net()).isEqualByComparingTo("0");
    }
    @Test
    void providesEqualTotalsWithTwoDifferentTransactionsForCaseD() {
        var sample = SettlementSamples.caseD();
        assertThat(sample.internalObligations()).hasSize(1);
        assertThat(sample.externalItems()).hasSize(2);
        assertThat(sample.externalItems().get(0).targetTid()).isEqualTo("T-TRANSFER");
        assertThat(sample.externalItems().get(0).amount()).isEqualByComparingTo("200000");
        assertThat(sample.externalItems().get(1).targetTid()).isEqualTo("T-EXTERNAL-ONLY");
        assertThat(sample.externalItems().get(1).amount()).isEqualByComparingTo("100000");
        assertThat(sample.expected().mismatchCount()).isEqualTo(2);
    }
}
