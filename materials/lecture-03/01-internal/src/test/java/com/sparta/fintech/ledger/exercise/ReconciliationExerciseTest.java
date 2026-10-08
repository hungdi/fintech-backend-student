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
            new BigDecimal("600000"), new BigDecimal("600000"), false, "상세 구성 확인");
        assertThat(result.getDifferenceAmount()).isEqualByComparingTo("0");
        assertThat(result.getResultStatus()).isEqualTo(ReconciliationResultStatus.MISMATCH);
    }
    @Test
    void nullCompletionTimeIsRejectedBeforeTransactionStateChanges() {
        // TODO [특강 1 / 3-1 복습, 특강 3 / 3-7-1] DmTransaction과 DmTransferOrder를 각각 준비하고 null 완료 시각으로 complete를 호출하세요.
        // null은 상태·completedAt·거래 및 전표 링크·전표번호 변경 전에 거절해야 합니다. 기존 값을 다시 조회해 보존을 검사하세요.
        // 두 모델의 처음 완료와 기존 완료 후 재호출을 나누어 검사하며, null로 기존 완료 시각을 지우지 못해야 합니다.
        // 잘못 저장된 과거 DB 기록을 찾는 통합 과제와 정상 입력을 막는 이 검증은 별도로 작성합니다.
        fail("TODO [특강 1 / 3-1 복습, 특강 3 / 3-7-1] null 완료 시각 거절과 변경 전 상태 보존을 확인하세요.");
    }

}
