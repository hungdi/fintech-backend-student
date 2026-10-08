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
        // TODO [특강 3 / 3-2-2] 마감 엔티티·업무일 과제를 완성한 뒤 createSampleData를 호출하세요.
        // 전일 VERIFIED 기초 0/0 + 당일 입금 1,000,000원·송금 300,000원으로 기대 잔액은 700,000/300,000원입니다.
        // 현재 잔액 대신 BASE_DATE의 CAPTURED 마감과 비교하고 두 잔액 결과가 NORMAL인지 확인하세요.
        // 전체 검사 과제를 완성한 뒤에는 불일치 0건, 당일 마감 VERIFIED와 검증 run 참조를 확인하세요.
        fail("TODO [특강 3 / 3-2-2] 전일 검증 마감과 당일 마감으로 일별 잔액을 비교하세요.");
    }

    @Test
    void includesANonZeroVerifiedOpeningBalance() {
        // TODO [특강 3 / 3-2-2] 출금 계좌의 전일 기초 500,000원, 초기 입금 1,000,000원, 송금 300,000원을 준비하세요.
        // 초기 입금 balanceAfter=1,500,000원, 송금 balanceAfter와 당일 원장 마감=1,200,000원으로 일관되게 맞춥니다.
        // 기대 금액과 실제 마감 금액이 모두 1,200,000원이어야 합니다. 전일 기초를 0으로 생략하면 실패해야 합니다.
        fail("TODO [특강 3 / 3-2-2] 거래 순액에 검증된 전일 기초 금액이 포함되는지 검사하세요.");
    }

    @Test
    void closingBatchPreservesThePriorDayBeforeNextDayPosting() {
        // TODO [특강 3 / 2-5-2, 5-3] 거래가 없는 날의 원장 잔액 1,000,000원과 명시적 기초 잔액을 준비하세요.
        // practiceClock으로 다음 UTC 날짜까지 진행하고, 잔액 행 잠금 안에서 prepareForPosting 후 100,000원을 입금하세요.
        // 이전 날짜 마감은 1,000,000원, 현재 원장 잔액은 1,100,000원이어야 합니다. 입금은 같은 postedAt으로 원장에 기록합니다.
        // capture를 재호출해도 과거 마감 금액·capturedAt이 유지되고, 과거 날짜 대사 기대 금액도 1,000,000원인지 확인하세요.
        fail("TODO [특강 3 / 2-5-2, 5-3] 다음 날 거래 전에 보관한 마감 금액과 재실행 불변을 확인하세요.");
    }

    @Test
    void limitsAllComparisonRulesToAHalfOpenUtcDay() {
        // TODO [특강 3 / 3-3~3-8, 6-3] 대상일 00:00은 포함하고 다음 날 00:00은 제외하도록 자료를 구성하세요.
        // 직전 날짜의 마지막 시각도 제외합니다. 거래·계좌거래·전표에는 선택한 동일 시각을 전달하세요.
        // 전일 기초에는 이전 날짜 거래가 이미 반영되어 있어야 합니다. 다른 날짜의 전표 누락을 당일 오류로 세지 마세요.
        // 순차적으로 각 검사 메서드를 완성하고, 규칙별 계좌·거래 결과와 전체 불일치 0건을 확인하세요.
        fail("TODO [특강 3 / 3-3~3-8, 6-3] UTC 날짜 경계와 각 검사 규칙의 대상 범위를 확인하세요.");
    }

    @Test
    void rejectsMissingOrUnverifiedClosingBeforeSavingResults() {
        // TODO [특강 3 / 3-2-1] 전일 마감 없음, 전일 CAPTURED만 존재, 당일 마감 없음 시나리오를 나누세요.
        // 각 시나리오에서 BASE_DATE는 이미 종료된 UTC 날짜여야 하며 새 DB 상태에서 시작합니다.
        // IllegalStateException을 확인하고 대사 실행·상세 결과가 추가되지 않았는지 검사하세요.
        // 현재 잔액을 조회해 누락된 과거 기초 금액을 자동으로 만들어서는 안 됩니다.
        fail("TODO [특강 3 / 3-2-1] 검증 완료 기초와 보관된 당일 마감의 선행 조건을 검사하세요.");
    }

    @Test
    void mismatchedClosingCannotServeAsTheNextDayOpening() {
        // TODO [특강 3 / 3-3~3-8, 6-3] JdbcTemplate의 직접 SQL로 테스트 DB의 입금 계좌 당일 마감만 999,000원으로 훼손하세요.
        // 이 호출은 오류 주입용입니다. 정상 Service나 setter의 금액 변경을 허용하거나 마감의 불변 제약을 제거하지 마세요.
        // 기대 300,000원, 실제 999,000원, 차액 699,000원과 MISMATCH를 기록하되 해당 마감은 CAPTURED여야 합니다.
        // 다음 날짜의 마감을 준비해도 검증되지 않은 전일 마감으로 대사를 진행할 수 없어야 합니다.
        // 이미 성공한 날의 원장을 훼손하고 재대사하면 그 날 이후 VERIFIED 상태도 해제되는지 확인하세요.
        fail("TODO [특강 3 / 3-3~3-8, 6-3] 불일치 마감과 이후 검증 기준의 상태를 확인하세요.");
    }

    @Test
    void failedNextDayTransferRollsBackCapturedClosingAndBusinessDate() {
        // TODO [특강 3 / 2-5-3, 3-8-1] 잔액 1,000,000/0원, 현재 업무일 D, 검증된 기초 D-1을 가진 두 계좌를 준비하세요.
        // practiceClock을 D+1로 진행한 뒤 실제 TransferService 경로에서 AFTER_LEDGER_POSTING 실패를 주입하세요.
        // 실패 후 별도 조회에서 D 마감이 생성되지 않았고 activeBusinessDate는 D, 두 잔액은 1,000,000/0원이어야 합니다.
        // 송금 오더·거래·계좌거래·전표·회계원장도 추가되면 안 됩니다. 4강에서는 완료 기록까지 함께 확인합니다.
        // 테스트 전체에 @Transactional을 붙여 롤백을 감추지 말고 Service 트랜잭션이 끝난 뒤 다시 읽으세요.
        // 선택: 실패 Hook을 해제하고 같은 키로 정상 재시도하면 새 송금과 전날 마감이 함께 저장되는지 확인하세요.
        fail("TODO [특강 3 / 2-5-3, 3-8-1] 날짜 전환의 마감 저장과 송금 원장 변경이 함께 롤백되는지 확인하세요.");
    }

    @Test
    void accountTransactionsAndAccountingLedgersAreComparedDirectly() {
        // TODO [특강 3 / 3-6, 6-3] InternalReconciliationTestSupport의 정상 샘플에서 매번 새로 시작하세요.
        // 회계원장 양쪽을 각 40만원으로 바꾸면 기대 60만원, 실제 80만원과 MISMATCH를 기록해야 합니다.
        // 전표 상세도 같은 금액으로 바꿔도 위 결과는 같습니다. 전표 상세 금액만 바꾸면 직접 대사 결과는 NORMAL입니다.
        // ACCOUNT_TRANSACTION_VS_ACCOUNTING_LEDGER의 대상은 거래 TID입니다. 잘못된 계좌, 방향, 계정과목과 날짜도 검증하세요.
        // 송금 출금 30만원과 입금 30만원, 회계원장 차변 30만원과 대변 20만원이면 차액은 -10만원입니다.
        fail("TODO [특강 3 / 3-6, 6-3] 변경한 테이블과 비교 규칙을 구분해 검사하세요.");
    }
    @Test
    void completedTransactionsWithoutCompletionTimeRemainLifecycleCandidates() {
        // TODO [특강 3 / 3-7-1, 6-3] 정상 완료 거래를 저장한 뒤 테스트 DB 직접 SQL로 completed_at만 null로 훼손하세요.
        // requestedAt만 대상일인 경우, 요청은 전날이지만 계좌거래 occurredAt이 대상일인 경우,
        // 요청·계좌거래는 범위 밖이지만 전표 postedAt이 대상일인 경우를 각각 새 DB에서 검사하세요.
        // COMPLETED_TRANSACTION_MISSING_COMPLETION_TIME의 MISMATCH이며 전체 실행은 불일치를 포함해야 합니다.
        // 세 시각 근거가 겹치면 같은 run에서 해당 거래 오류는 한 번만 남아야 합니다.
        // 근거가 서로 다른 날짜에 있으면 각 날짜별 새 DB와 선행 마감을 준비해 오류 후보를 확인하세요. 전체 기간 조회로 대체하지 마세요.
        // 정상 완료 시각의 반열린 UTC 범위 조회는 유지하고, 원본 시각을 요청·원장 시각으로 자동 보정하지 않습니다.
        fail("TODO [특강 3 / 3-7-1, 6-3] 완료 시각이 사라진 거래의 후보 조회와 중복 없는 오류 기록을 확인하세요.");
    }

    @Test
    void apiOrderCompletionTimesMustMatchInBothDirections() {
        // TODO [특강 3 / 3-7-1, 6-3] 송금 완료 거래와 대응 완료 오더가 같은 Instant를 갖는 정상 자료를 준비하세요.
        // 테스트 DB 직접 SQL로 같은 UTC 날짜 안에서 두 완료 시각을 1초 다르게 만드세요.
        // 거래만 null, 오더만 null인 사례도 각각 검사합니다. null 시각은 서로 같다고 간주하지 않습니다.
        // 거래→오더 검사와 완료 오더→거래 역방향 검사 모두 API_TRANSFER_ORDER의 MISMATCH를 찾아야 합니다.
        // 오더만 다음 UTC 날짜로 바꾼 사례는 각 날짜별 새 DB·검증된 전일/당일 마감과 종료일 이후 Clock으로 검사하세요.
        // 앞 날짜의 실패가 다음 날짜 대사의 선행 조건을 깨뜨리지 않도록 두 방향의 사례를 분리합니다.
        // 정상적으로 양방향 후보에 포함된 같은 오더는 한 run에서 결과를 중복 추가하지 않아야 합니다.
        // 날짜 필터에서 null 행이 빠진다는 이유로 정상 또는 비교 대상 없음으로 처리하지 않습니다.
        // 유효한 same-Instant 사례는 NORMAL이며 재실행으로 금융 원본을 보정하지 않습니다.
        fail("TODO [특강 3 / 3-7-1, 6-3] API 거래와 송금 오더의 정확한 완료 시각을 양방향으로 대조하세요.");
    }


    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(strings = {"split-credit", "same-direction", "unequal-items", "swapped-accounts", "same-account", "wrong-date", "wrong-currency", "missing-order"})
    void transferRequiresExactAccountItemsAndCompletedOrder(String corruption) {
        // TODO [특강 3 / 3-3, 3-7-1] createSampleData()의 30만원 송금에 각각 독립적으로 오류를 주입하세요.
        // 출금 30만원·입금 20만원·입금 10만원은 합계가 같아도 입금 2건이므로 MISMATCH입니다.
        // 출금/입금 모두 DEBIT, 출금 40만원·입금 20만원, 계좌 교환, 동일 계좌도 각각 MISMATCH여야 합니다.
        // 날짜 또는 통화 변경과 완료 오더 삭제도 검사하세요. 거래별 결과를 TID와 대상 유형으로 선택합니다.
        // 금액 합계가 같은 사례에서는 differenceAmount가 0이어도 resultStatus는 MISMATCH인지 확인하세요.
        org.junit.jupiter.api.Assertions.fail("TODO [특강 3 / 3-3, 3-7-1] 송금 상세 구조와 완료 오더를 검증하세요.");
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(strings = {"offsetting-amounts", "same-direction", "wrong-account-code", "split-credit", "wrong-account", "wrong-date"})
    void equalLedgerTotalsDoNotHideIncorrectIndividualRecords(String corruption) {
        // TODO [특강 3 / 3-4, 6-3] 정상 30만원 송금의 회계원장에 각 오류를 독립적으로 주입하세요.
        // 차변 40만원과 대변 20만원, 같은 방향 두 건, 잘못된 계정과목, 대변 20만원과 10만원 분할을 검사합니다.
        // 계좌 또는 날짜만 바뀐 사례도 포함합니다. 합계 60만원과 차액 0원이어도 MISMATCH여야 합니다.
        // 새 대사 유형 ACCOUNT_TRANSACTION_VS_ACCOUNTING_LEDGER와 거래 TID로 결과를 선택하세요.
        // 대사 전후 금융 원본의 값을 조회해 비교 서비스가 기록을 수정하지 않았는지 확인하세요.
        fail("TODO [특강 3 / 3-4, 6-3] 계좌원장과 회계원장의 개별 내역을 직접 비교하세요.");
    }

}
