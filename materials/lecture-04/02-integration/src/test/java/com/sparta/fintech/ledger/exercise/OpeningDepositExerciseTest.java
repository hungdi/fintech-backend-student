package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.support.SecurityTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("exercise")
class OpeningDepositExerciseTest extends SecurityTestSupport {
    @Test
    void openingDepositWithHeldFundsRecordsLedgerBalance() {
        // TODO [특강 4 / 8-4] 원장 금액 100,000원, 사용가능잔액 80,000원인 계좌와 고객예수금 코드를 준비하세요.
        // 대사도 연결한다면 첫 원장 기록 전에 initializeOpeningBalance로 원장 금액 100,000원의 기초를 등록합니다.
        // TransactionTemplate 안에서 FinancialTestData.openingDeposit에 DailyClosingService와 Clock도 넘겨 100,000원을 입금합니다.
        // 커밋 후 다시 읽은 두 잔액은 200,000원과 180,000원, 계좌거래 balanceAfter는 200,000원이어야 합니다.
        fail("TODO [특강 4 / 8-4] 지급보류 입금의 원장 금액 기록을 확인하세요.");
    }
    @Test
    void loginTransferReplayAndReconciliationUseCompleteOpeningLedger() {
        // TODO [특강 4 / 8-4] 두 계좌의 최초 거래일과 0원 기초를 initializeOpeningBalance로 등록하세요.
        // 0원 계좌에 FinancialTestData로 1,000,000원을 초기 입금하고 로그인 후 300,000원 송금과 재시도를 수행합니다.
        // 두 잔액 700,000원과 300,000원, 송금 오더 및 완료 기록 각 1건을 먼저 검사하세요.
        // HTTP 요청을 마친 뒤 practiceClock으로 다음 UTC 날짜로 진행하고 당일 마감 capture 후 같은 날짜를 대사하세요.
        // 불일치 0건, 마감 금액 700,000/300,000원, VERIFIED 상태와 검증 실행 참조가 있어야 합니다.
        // 다음 날 현재 잔액을 전날 마감으로 새로 덮어쓰지 마세요. 이후 HTTP 검증은 토큰 시각과 Clock을 맞춰 수행합니다.
        fail("TODO [특강 4 / 8-4] 초기 원장을 포함한 통합 시나리오를 작성하세요.");
    }
}
