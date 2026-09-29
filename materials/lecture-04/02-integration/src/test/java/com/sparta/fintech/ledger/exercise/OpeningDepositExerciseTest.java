package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.support.SecurityTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("exercise")
class OpeningDepositExerciseTest extends SecurityTestSupport {
    @Test
    void openingDepositWithHeldFundsRecordsLedgerBalance() {
        // TODO [특강 4 / 8-4] 원장 금액 100원, 사용가능잔액 80원인 계좌와 고객예수금 코드를 준비하세요.
        // TransactionTemplate 안에서 FinancialTestData.openingDeposit으로 100원을 입금합니다.
        // 커밋 후 다시 읽은 두 잔액은 200원과 180원, 계좌거래 balanceAfter는 200원이어야 합니다.
        fail("TODO [특강 4 / 8-4] 지급보류 입금의 원장 금액 기록을 확인하세요.");
    }
    @Test
    void loginTransferReplayAndReconciliationUseCompleteOpeningLedger() {
        // TODO [특강 4 / 8-4] 0원 계좌에 1000원을 초기 입금하고 로그인 후 300원 송금과 재시도를 수행하세요.
        // 두 잔액 700원과 300원, 송금 오더 및 완료 기록 각 1건, 대사 불일치 0건을 검사하세요.
        fail("TODO [특강 4 / 8-4] 초기 원장을 포함한 통합 시나리오를 작성하세요.");
    }
}
