package com.sparta.fintech.ledger.exercise;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("exercise")
class SettlementExerciseTest {
    @Test
    void suppliedFilesDetectDifferencesMissingRowsAndOffsettingTotals() {
        // TODO [특강 3 / 4-6-2] ReconciliationTestSupport.createSampleData로 완료 오더를 준비하세요.
        // SettlementJson.read의 input만 calculate에 전달하고 expected와 저장된 상세를 TID별로 비교하세요.
        // 6개 정상 형식 파일을 각각 새 DB 상태에서 검사하세요. 재실행 시 새 헤더와 같은 비교 결과도 확인하세요.
        fail("TODO [특강 3 / 4-6-2] 제공 자료의 상세 결과와 합계를 검사하세요.");
    }
    @Test
    void invalidInputIsRejectedWithoutSavingResults() {
        // TODO [특강 3 / 4-6-3] invalid 아래 7개 파일을 읽고 예외 및 저장 전후 정산 행 수를 검사하세요.
        fail("TODO [특강 3 / 4-6-3] 입력 오류 시 헤더와 상세가 추가되지 않는지 확인하세요.");
    }
}
