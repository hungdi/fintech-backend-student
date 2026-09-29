package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.support.InternalReconciliationTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

/** 상위 클래스의 초기화와 샘플 생성을 사용하고 필요한 Service와 조회 검증을 추가하세요. */
@Tag("exercise")
class ReconciliationIntegrationExerciseTest extends InternalReconciliationTestSupport {
    @Test
    void ledgerOnlyAndJournalAndLedgerCorruptionHaveDifferentResults() {
        // TODO [특강 3 / 3-6, 6-3] InternalReconciliationTestSupport의 정상 샘플에서 매번 새로 시작하세요.
        // 원장만 400원으로 바꾼 경우: 요청 대 전표 NORMAL, 전표 대 원장 MISMATCH, 차대 합계 NORMAL.
        // 전표 상세도 함께 400원으로 바꾼 경우: 요청 대 전표 MISMATCH, 전표 대 원장 MISMATCH, 차대 합계 NORMAL.
        // 고객 계좌만 다른 정상 계좌로 바꾼 경우도 별도로 검증하세요.
        fail("TODO [특강 3 / 3-6, 6-3] 변경한 테이블과 비교 규칙을 구분해 검사하세요.");
    }
}
