package com.sparta.fintech.ledger.preparation;

import com.sparta.fintech.ledger.support.SettlementJson;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
class SettlementMaterialsSmokeTest {
    @ParameterizedTest
    @ValueSource(strings={"normal.json","amount-mismatch.json","missing-bank.json","external-only.json", "offsetting-differences.json","zero-external-only.json"})
    void readsInputAndExpectedSeparately(String name) throws Exception {
        var sample = SettlementJson.read(name);
        assertThat(sample.input().externalInstitutionCode()).isEqualTo("OTHER-BANK");
        assertThat(sample.expected().has("details")).isTrue();
        assertThat(sample.expectedError()).isNull();
    }
    @ParameterizedTest
    @ValueSource(strings={"invalid/amount-overflow.json","invalid/duplicate-tid.json","invalid/duplicate-tid-different-amount.json","invalid/missing-amount.json","invalid/missing-tid.json","invalid/negative-amount.json","invalid/too-many-decimal-places.json"})
    void readsInvalidInputWithoutTreatingItAsValidBusinessData(String name) throws Exception {
        var sample = SettlementJson.read(name);
        assertThat(sample.expectedError()).isNotBlank();
        assertThat(sample.expected()).isNull();
    }
}
