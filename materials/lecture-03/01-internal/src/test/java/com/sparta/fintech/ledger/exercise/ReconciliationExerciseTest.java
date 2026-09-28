package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.domain.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Test
    void ledgerOnlyAndJournalAndLedgerCorruptionHaveDifferentResults() {
        // TODO [특강 3 / 3-6, 6-3] InternalReconciliationTestSupport의 정상 샘플에서 매번 새로 시작하세요.
        // 원장만 400원으로 바꾼 경우: 요청 대 전표 NORMAL, 전표 대 원장 MISMATCH, 차대 합계 NORMAL.
        // 전표 상세도 함께 400원으로 바꾼 경우: 요청 대 전표 MISMATCH, 전표 대 원장 MISMATCH, 차대 합계 NORMAL.
        // 고객 계좌만 다른 정상 계좌로 바꾼 경우도 별도로 검증하세요.
        fail("TODO [특강 3 / 3-6, 6-3] 변경한 테이블과 비교 규칙을 구분해 검사하세요.");
    }
}
