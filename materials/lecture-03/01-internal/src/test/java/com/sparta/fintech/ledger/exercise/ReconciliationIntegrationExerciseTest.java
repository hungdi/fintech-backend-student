package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.support.InternalReconciliationTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

/** 상위 클래스의 초기화와 샘플 생성을 사용하고 필요한 Service와 조회 검증을 추가하세요. */
@Tag("exercise")
class ReconciliationIntegrationExerciseTest extends InternalReconciliationTestSupport {
    @Test
    void detectsSplitCreditAccountTransactions() {
        // TODO [특강 3 / 3-3] 정상 30만원 송금의 입금 내역을 20만원으로 변경하고 같은 거래·계좌에 입금 10만원을 추가하세요.
        // 계좌 잔액 대사는 NORMAL, 거래별 대사는 합계 60만원·차액 0원이어도 내역이 3건이므로 MISMATCH여야 합니다.
        // 실행이 정상 종료되면 불일치가 있어도 ReconciliationRunStatus.COMPLETED인지 확인하세요.
        fail("TODO [특강 3 / 3-3] 분할 입금으로 상세 건수가 달라진 거래를 검출하세요.");
    }

    @Test
    void detectsMissingAccountingLedgers() {
        // TODO [특강 3 / 3-4] createSampleData()의 송금 거래 ID로 연결된 회계원장을 모두 삭제하고 대사하세요.
        // ACCOUNT_TRANSACTION_VS_ACCOUNTING_LEDGER의 MISMATCH 1건, 대상 TID T-TRANSFER를 확인하세요.
        // 기대 600000원, 실제 0원, 차액 -600000원이며 새 대사 메서드를 추가하지 않습니다.
        fail("TODO [특강 3 / 3-4] 기존 직접 대사로 회계원장 전체 누락을 검출하세요.");
    }

    @Test
    void normalReconciliationHasSixResultsAndNoMismatch() {
        // TODO [특강 3 / 3-5] createSampleData()로 1,000,000원 입금과 300,000원 송금 자료를 준비하고 BASE_DATE를 대사하세요.
        // totalCount=6, mismatchCount=0, 세 대사 유형별 2건, 저장된 결과 6건 모두 NORMAL인지 확인하세요.
        // 두 계좌의 700,000원/300,000원 마감 잔액은 유지되고 상태가 VERIFIED인지 확인하세요.
        fail("TODO [특강 3 / 3-5] 세 가지 대사의 정상 결과와 마감 검증 상태를 확인하세요.");
    }

    @Test
    void finalReconciliationHasOneBalanceMismatch() {
        // TODO [특강 3 / 3-5] 정상 샘플의 입금 계좌·BASE_DATE 마감 잔액만 SQL로 999,000원으로 변경하세요.
        // totalCount=6, mismatchCount=1, 기대 300,000원·실제 999,000원·차액 699,000원인지 확인하세요.
        // 실행 상태는 COMPLETED이며 두 계좌 마감은 CAPTURED, verifiedRun과 verifiedAt은 null이어야 합니다.
        // 현재 잔액이나 금융 원본을 대사 서비스가 변경하지 않았는지도 확인하세요.
        fail("TODO [특강 3 / 3-5] 마감 잔액 불일치 1건과 실행 완료·마감 상태를 확인하세요.");
    }

    @Test
    void usesVerifiedOpeningAndCapturedClosingForDailyAmounts() {
        // TODO [특강 3 / 3-2-2] 마감 엔티티·업무일 과제를 완성한 뒤 createSampleData를 호출하세요.
        // 전일 VERIFIED 기초 0/0 + 당일 입금 1,000,000원·송금 300,000원으로 기대 잔액은 700,000/300,000원입니다.
        // 현재 잔액 대신 BASE_DATE의 CAPTURED 마감과 비교하고 두 잔액 결과가 NORMAL인지 확인하세요.
        // 전체 검사 과제를 완성한 뒤에는 세 유형별 2건씩 totalCount=6, mismatchCount=0, 당일 마감 VERIFIED와 검증 run 참조를 확인하세요.
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
    void rejectsMissingOrUnverifiedClosingBeforeSavingResults() {
        // TODO [특강 3 / 3-2-1] 전일 마감 없음, 전일 CAPTURED만 존재, 당일 마감 없음 시나리오를 나누세요.
        // 각 시나리오에서 BASE_DATE는 이미 종료된 UTC 날짜여야 하며 새 DB 상태에서 시작합니다.
        // IllegalStateException을 확인하고 대사 실행·상세 결과가 추가되지 않았는지 검사하세요.
        // 현재 잔액을 조회해 누락된 과거 기초 금액을 자동으로 만들어서는 안 됩니다.
        fail("TODO [특강 3 / 3-2-1] 검증 완료 기초와 보관된 당일 마감의 선행 조건을 검사하세요.");
    }

    @Test
    void accountTransactionsAndAccountingLedgersAreComparedDirectly() {
        // TODO [특강 3 / 3-4, 6-3] InternalReconciliationTestSupport의 정상 샘플에서 매번 새로 시작하세요.
        // 회계원장 양쪽을 각 40만원으로 바꾸면 기대 60만원, 실제 80만원과 MISMATCH를 기록해야 합니다.
        // 전표 상세도 같은 금액으로 바꿔도 위 결과는 같습니다. 전표 상세 금액만 바꾸면 직접 대사 결과는 NORMAL입니다.
        // ACCOUNT_TRANSACTION_VS_ACCOUNTING_LEDGER의 대상은 거래 TID입니다. 잘못된 계좌, 방향, 계정과목과 날짜도 검증하세요.
        // 송금 출금 30만원과 입금 30만원, 회계원장 차변 30만원과 대변 20만원이면 차액은 -10만원입니다.
        fail("TODO [특강 3 / 3-4, 6-3] 변경한 테이블과 비교 규칙을 구분해 검사하세요.");
    }

    @org.junit.jupiter.params.ParameterizedTest
    @org.junit.jupiter.params.provider.ValueSource(strings = {"split-credit", "same-direction", "unequal-items", "swapped-accounts", "same-account", "wrong-date", "wrong-currency", "missing-order"})
    void transferRequiresExactAccountItemsAndOrderAccounts(String corruption) {
        // TODO [특강 3 / 3-3, 3-3] createSampleData()의 30만원 송금에 각각 독립적으로 오류를 주입하세요.
        // 출금 30만원·입금 20만원·입금 10만원은 합계가 같아도 입금 2건이므로 MISMATCH입니다.
        // 출금/입금 모두 DEBIT, 출금 40만원·입금 20만원, 계좌 교환, 동일 계좌도 각각 MISMATCH여야 합니다.
        // 날짜 또는 통화 변경과 송금 오더 삭제도 검사하세요. 거래별 결과를 TID와 대상 유형으로 선택합니다.
        // 금액 합계가 같은 사례에서는 differenceAmount가 0이어도 resultStatus는 MISMATCH인지 확인하세요.
        org.junit.jupiter.api.Assertions.fail("TODO [특강 3 / 3-3, 3-3] 송금 상세 구조와 오더의 출금·입금 계좌를 검증하세요.");
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
