package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.support.SettlementTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

/** 매핑·업무 구현 후 fail을 실제 Service 호출과 DB assertion으로 교체하세요. */
@Tag("exercise")
class SettlementExerciseTest extends SettlementTestSupport {
    @Test
    void caseAProducesNetPayableAndLinksTheNormalExternalRun() {
        // TODO [특강 3 / 정산-5~정산-8] caseA를 createSampleObligations로 적재하세요.
        // reconcileSettlementObligations(date, institution, currency, sample.externalItems()) 후 calculate(scope, runId).
        // payable=300000, receivable=100000, net=200000, CALCULATED, 외부 대사 ID/FK를 확인하세요.
        // confirm(settlementId)는 CONFIRMED로 변경하지만 실제 송금·자금 지급 기록은 생성하지 않습니다.
        fail("TODO [특강 3 / 정산-8] 사례 A의 지급·수취 집계, 대사 연결과 담당자 확인을 검증하세요.");
    }
    @Test
    void caseBProducesNegativeNetReceivable() {
        // TODO [특강 3 / 정산-6~정산-8] caseB: payable=100000, receivable=300000, net=-200000.
        // 음수는 우리 기관의 순수취 방향입니다. BigDecimal 비교와 CONFIRMED 전이를 확인하세요.
        fail("TODO [특강 3 / 정산-8] 사례 B의 순수취 금액을 검증하세요.");
    }
    @Test
    void caseCProducesZeroNetSettlement() {
        // TODO [특강 3 / 정산-6~정산-8] caseC: payable=receivable=300000, net=0.
        // 0원도 유효한 CALCULATED/CONFIRMED 결과이며 실제 지급 처리와 구분합니다.
        fail("TODO [특강 3 / 정산-8] 사례 C의 상계 후 0원을 검증하세요.");
    }
    @Test
    void caseDStoresMismatchAndCalculationButCannotConfirm() {
        // TODO [특강 3 / 정산-7~정산-8] caseD: 내부·외부 합계는 모두300000, differenceAmount=0.
        // T-TRANSFER 금액200000과 T-EXTERNAL-ONLY 금액100000이 상세 불일치2건을 만들어야 합니다.
        // calculate는 payable=300000, receivable=0, net=300000의 CALCULATED 참고 금액을 저장합니다.
        // confirm은 예외여야 하며 CALCULATED 상태, 외부 대사 헤더·상세가 모두 DB에 남아야 합니다.
        fail("TODO [특강 3 / 정산-8] 합계가 같아도 거래별 불일치이면 정산 확정을 거절하세요.");
    }
    @Test
    void rejectsUnrelatedLegacyOrStaleReconciliationAtCalculationAndConfirmation() {
        // TODO [특강 3 / 정산-7~정산-8] 기관/기준일/통화가 다른 ID와 TRANSFER_ORDER 출처 ID를 각각 거절하세요.
        // 대사 후 표본의 TID/GID/금액/방향/기관/통화/날짜를 바꾸면 fingerprint가 달라집니다.
        // calculate와 confirm이 변경된 자료를 거절하고 기존 정산 행을 보존하는지 검사하세요.
        // 최신 대사가 MISMATCH이면 과거 NORMAL ID로 calculate/confirm해 확정을 우회할 수 없어야 합니다.
        // 같은 TID와 금액이라도 외부 지급 방향이 반대면 MISMATCH/확정 거절이어야 합니다.
        // 표본 외부 자료에서 null 방향은 저장 전 입력 예외이고 기존 대사·정산 결과는 보존되어야 합니다.
        fail("TODO [특강 3 / 정산-8] 외부 대사 범위·출처·최신성·원본 동일성을 검증하세요.");
    }
    @Test
    void repeatedCalculationUpdatesOneDraftAndProtectsConfirmedResults() {
        // TODO [특강 3 / 정산-7~정산-8] 같은 범위에 재대사 후 calculate하면 CALCULATED의 같은 ID를 갱신합니다.
        // (base_date, external_institution_code, currency_code) UNIQUE와 새 외부 대사 연결을 확인하세요.
        // CONFIRMED 후 재계산은 예외이며 금액/대사 FK/상태가 바뀌지 않아야 합니다.
        // 최신 정상 자료가 변하지 않은 confirm 재호출의 처리 규칙도 검증하세요.
        fail("TODO [특강 3 / 정산-8] 동일 범위 재실행과 확정 결과 보존을 검증하세요.");
    }
    @Test
    void calculationAndConfirmationRollBackWhenSavingFails() {
        // TODO [특강 3 / 정산-8] Spring 프록시 Service 호출에서 Repository 저장/flush 실패를 주입하세요.
        // 새 계산 실패는 정산 행0건, 기존 CALCULATED 갱신 실패는 이전 금액·FK 보존을 확인합니다.
        // confirm 저장 실패는 CALCULATED로 되돌아가고 외부 대사 헤더·상세와 금융 원본은 유지됩니다.
        // 테스트 자체의 @Transactional 롤백으로 Service의 트랜잭션 누락을 숨기지 마세요.
        fail("TODO [특강 3 / 정산-8] 저장 실패 시 계산·확정의 전체 롤백을 검증하세요.");
    }
    @Test
    void scopesObligationsAndRejectsInvalidOrOverflowingAmounts() {
        // TODO [특강 3 / 정산-4~정산-8] 다른 날짜·기관·통화 표본이 합계에 포함되지 않는지 검사하세요.
        // 표본의 null/음수/0/저장범위 초과/소수 자릿수, 누락된 방향과 중복 범위+TID를 거절하세요.
        // 각각 유효한 금액의 합계가 DECIMAL(19,2)를 넘는 경우도 저장 전에 예외여야 합니다.
        fail("TODO [특강 3 / 정산-8] 정산 표본의 범위·필수값·금액과 합계 저장 범위를 검증하세요.");
    }
}
