package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.support.InternalReconciliationTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

/** 상위 클래스의 초기화와 샘플 생성을 사용하고 필요한 Service와 조회 검증을 추가하세요. */
@Tag("exercise")
class ReconciliationIntegrationExerciseTest extends InternalReconciliationTestSupport {
    @Test
    void usesVerifiedOpeningAndCapturedClosingForDailyAmounts() {
        // TODO [특강 3 / 3-2 확장] 마감 엔티티·업무일 과제를 완성한 뒤 createSampleData를 호출하세요.
        // 전일 VERIFIED 기초 0/0 + 당일 입금 1000원·송금 300원으로 기대 잔액은 700/300원입니다.
        // 현재 잔액 대신 BASE_DATE의 CAPTURED 마감과 비교하고 두 잔액 결과가 NORMAL인지 확인하세요.
        // 전체 검사 과제를 완성한 뒤에는 불일치 0건, 당일 마감 VERIFIED와 검증 run 참조를 확인하세요.
        fail("TODO [특강 3 / 3-2 확장] 전일 검증 마감과 당일 마감으로 일별 잔액을 비교하세요.");
    }

    @Test
    void includesANonZeroVerifiedOpeningBalance() {
        // TODO [특강 3 / 3-2 확장] 출금 계좌의 전일 기초 500원, 초기 입금 1000원, 송금 300원을 준비하세요.
        // 초기 입금 balanceAfter=1500원, 송금 balanceAfter와 당일 원장 마감=1200원으로 일관되게 맞춥니다.
        // 기대 금액과 실제 마감 금액이 모두 1200원이어야 합니다. 전일 기초를 0으로 생략하면 실패해야 합니다.
        fail("TODO [특강 3 / 3-2 확장] 거래 순액에 검증된 전일 기초 금액이 포함되는지 검사하세요.");
    }

    @Test
    void closingBatchPreservesThePriorDayBeforeNextDayPosting() {
        // TODO [특강 3 / 3-2 확장, 5-3] 거래가 없는 날의 원장 잔액 1000원과 명시적 기초 잔액을 준비하세요.
        // practiceClock으로 다음 UTC 날짜까지 진행하고, 잔액 행 잠금 안에서 prepareForPosting 후 100원을 입금하세요.
        // 이전 날짜 마감은 1000원, 현재 원장 잔액은 1100원이어야 합니다. 입금은 같은 postedAt으로 원장에 기록합니다.
        // capture를 재호출해도 과거 마감 금액·capturedAt이 유지되고, 과거 날짜 대사 기대 금액도 1000원인지 확인하세요.
        fail("TODO [특강 3 / 3-2 확장, 5-3] 다음 날 거래 전에 보관한 마감 금액과 재실행 불변을 확인하세요.");
    }

    @Test
    void limitsAllComparisonRulesToAHalfOpenUtcDay() {
        // TODO [특강 3 / 3-2 확장, 6-3] 대상일 00:00은 포함하고 다음 날 00:00은 제외하도록 자료를 구성하세요.
        // 직전 날짜의 마지막 시각도 제외합니다. 거래·계좌거래·전표에는 선택한 동일 시각을 전달하세요.
        // 전일 기초에는 이전 날짜 거래가 이미 반영되어 있어야 합니다. 다른 날짜의 전표 누락을 당일 오류로 세지 마세요.
        // 순차적으로 각 검사 메서드를 완성하고, 규칙별 계좌·거래 결과와 전체 불일치 0건을 확인하세요.
        fail("TODO [특강 3 / 3-2 확장, 6-3] UTC 날짜 경계와 각 검사 규칙의 대상 범위를 확인하세요.");
    }

    @Test
    void rejectsMissingOrUnverifiedClosingBeforeSavingResults() {
        // TODO [특강 3 / 3-2 확장] 전일 마감 없음, 전일 CAPTURED만 존재, 당일 마감 없음 시나리오를 나누세요.
        // 각 시나리오에서 BASE_DATE는 이미 종료된 UTC 날짜여야 하며 새 DB 상태에서 시작합니다.
        // IllegalStateException을 확인하고 대사 실행·상세 결과가 추가되지 않았는지 검사하세요.
        // 현재 잔액을 조회해 누락된 과거 기초 금액을 자동으로 만들어서는 안 됩니다.
        fail("TODO [특강 3 / 3-2 확장] 검증 완료 기초와 보관된 당일 마감의 선행 조건을 검사하세요.");
    }

    @Test
    void mismatchedClosingCannotServeAsTheNextDayOpening() {
        // TODO [특강 3 / 3-2 확장, 6-3] JdbcTemplate의 직접 SQL로 테스트 DB의 입금 계좌 당일 마감만 999원으로 훼손하세요.
        // 이 호출은 오류 주입용입니다. 정상 Service나 setter의 금액 변경을 허용하거나 마감의 불변 제약을 제거하지 마세요.
        // 기대 300원, 실제 999원, 차액 699원과 MISMATCH를 기록하되 해당 마감은 CAPTURED여야 합니다.
        // 다음 날짜의 마감을 준비해도 검증되지 않은 전일 마감으로 대사를 진행할 수 없어야 합니다.
        // 이미 성공한 날의 원장을 훼손하고 재대사하면 그 날 이후 VERIFIED 상태도 해제되는지 확인하세요.
        fail("TODO [특강 3 / 3-2 확장, 6-3] 불일치 마감과 이후 검증 기준의 상태를 확인하세요.");
    }

    @Test
    void failedNextDayTransferRollsBackCapturedClosingAndBusinessDate() {
        // TODO [특강 3 / 3-2 확장] 잔액 1000/0원, 현재 업무일 D, 검증된 기초 D-1을 가진 두 계좌를 준비하세요.
        // practiceClock을 D+1로 진행한 뒤 실제 TransferService 경로에서 AFTER_LEDGER_POSTING 실패를 주입하세요.
        // 실패 후 별도 조회에서 D 마감이 생성되지 않았고 activeBusinessDate는 D, 두 잔액은 1000/0원이어야 합니다.
        // 송금 오더·거래·계좌거래·전표·회계원장도 추가되면 안 됩니다. 4강에서는 완료 기록까지 함께 확인합니다.
        // 테스트 전체에 @Transactional을 붙여 롤백을 감추지 말고 Service 트랜잭션이 끝난 뒤 다시 읽으세요.
        // 선택: 실패 Hook을 해제하고 같은 키로 정상 재시도하면 새 송금과 전날 마감이 함께 저장되는지 확인하세요.
        fail("TODO [특강 3 / 3-2 확장] 날짜 전환의 마감 저장과 송금 원장 변경이 함께 롤백되는지 확인하세요.");
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
