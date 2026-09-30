package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.domain.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("exercise")
class ReconciliationExerciseTest {
    @Test
    void equalTotalsDoNotHideMissingStructure() {
        var date = LocalDate.of(2026, 9, 15);
        var run = new SsReconciliationRun(date, "비교 연습");
        var result = new SiReconciliationResult(run, ReconciliationTargetType.TRANSACTION_VS_ACCOUNT_TRANSACTION,
            date, "T-TRANSFER", "G-TRANSFER", null,
            new BigDecimal("600"), new BigDecimal("600"), false, "상세 구성 확인");
        assertThat(result.getDifferenceAmount()).isEqualByComparingTo("0");
        assertThat(result.getResultStatus()).isEqualTo(ReconciliationResultStatus.MISMATCH);
    }
}
