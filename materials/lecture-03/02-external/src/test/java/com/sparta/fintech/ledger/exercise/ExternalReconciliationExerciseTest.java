package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.support.ExternalReconciliationTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("exercise")
class ExternalReconciliationExerciseTest extends ExternalReconciliationTestSupport {
    @Test
    void suppliedFilesDetectDifferencesMissingRowsAndOffsettingTotals() {
        // TODO [특강 3 / 4-6-2] ExternalReconciliationTestSupport.createSampleData로 완료 오더를 준비하세요.
        // ExternalReconciliationJson.read의 input만 reconcile에 전달하고 expected와 저장된 상세를 TID별로 비교하세요.
        // 6개 정상 형식 파일을 각각 새 DB 상태에서 검사하세요. 재실행 시 새 헤더와 같은 비교 결과도 확인하세요.
        // 별도 externalSourceSnapshot으로 마감 테이블의 모든 컬럼까지 실행 전후 비교하세요.
        // 내부 대사의 검증 상태 변경은 이 외부 대사의 원본 보존 검사와 공유하지 않습니다.
        fail("TODO [특강 3 / 4-6-2] 제공 자료의 상세 결과와 합계를 검사하세요.");
    }
    @Test
    void invalidInputIsRejectedWithoutSavingResults() {
        // TODO [특강 3 / 4-6-3] invalid 아래 7개 파일을 읽고 예외 및 저장 전후 외부 대사 행 수를 검사하세요.
        // 정상 외부 대사 결과를 먼저 저장한 경우 기존 헤더·상세와 externalSourceSnapshot도 그대로여야 합니다.
        fail("TODO [특강 3 / 4-6-3] 입력 오류 시 헤더와 상세가 추가되지 않는지 확인하세요.");
    }
    @Test
    void externalReconciliationPreservesAllDailyClosingColumns() {
        // TODO [특강 3 / 4-6-2~4-6-3] 정상 외부 대사, 입력 거절, 같은 자료 재실행을 각각 검사하세요.
        // 금융 원본 9개 테이블에 sm_account_daily_closing을 더해 모든 행·컬럼을 안정적인 순서로 비교합니다.
        // CAPTURED 기록과 이미 VERIFIED인 기록을 별도로 준비하세요. verified_run_id와 verified_at도 포함합니다.
        // 내부 ReconciliationService.run은 검증 상태를 바꾸므로 이 테스트의 reconcile 전후 사이에 호출하지 않습니다.
        // 외부 대사는 마감 금액·기준일·계좌·상태·보관 시각·기초 표시·검증 참조·감사 시각을 모두 유지해야 합니다.
        // 마감 원본 검사는 sm_external_reconciliation/si_external_reconciliation_detail의 신규 결과 저장 검사와 구분합니다.
        fail("TODO [특강 3 / 4-6-2~4-6-3] 외부 대사가 일별 마감의 모든 원본 컬럼을 보존하는지 확인하세요.");
    }

    @Test
    void scopeValidationAndStorageFailurePreserveExistingResultsAndSourceRows() {
        // TODO [특강 3 / 4-5~4-6] UTC [startAt,endAt), 완료 상태, 기관 및 통화의 범위 조건을 검사하세요.
        // null 기준일/목록/목록 원소, 공백/길이초과 기관·TID·선택 GID와 통화 형식오류를 저장 전에 거절하세요.
        // 헤더 저장 뒤 상세 save/flush 실패를 주입하면 새 헤더와 상세가 함께 롤백되어야 합니다.
        // 기존 외부 대사 결과와 금융·마감 원본 snapshot은 모두 유지되는지 확인하세요.
        fail("TODO [특강 3 / 4-6] 외부 대사의 범위·입력 검증과 저장 실패 롤백을 검증하세요.");
    }

    private java.util.Map<String, java.util.List<java.util.Map<String, Object>>> externalSourceSnapshot() {
        // TODO [특강 3 / 4-6-2] 금융 원본 9개 테이블과 sm_account_daily_closing을 테스트 DB에서 읽어 반환하세요.
        // 각 테이블의 모든 컬럼과 모든 행을 PK 기준의 안정적인 순서로 보관하고 외부 대사 결과 테이블은 제외합니다.
        // 내부 대사의 상태 변경을 허용하는 snapshot과 이 메서드를 공유하지 않습니다.
        throw new UnsupportedOperationException("TODO [특강 3 / 4-6-2] 외부 대사용 금융·마감 원본 스냅샷을 작성하세요.");
    }

}
