package com.sparta.fintech.ledger.exercise;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("exercise")
class DomainDesignExerciseTest {
    @Test
    void customerAccountAndBalanceConstraints() {
        // TODO [특강 1 / 1-2, 2-1] JPA 매핑 후 test 프로필의 DB 테스트로 전환하세요.
        // 고객을 저장한 뒤 반환된 ID로 계좌와 잔액을 연결합니다.
        // 중복 계좌번호, 없는 고객 참조, 같은 계좌의 두 번째 잔액 행을 각각 저장하고 제약을 확인하세요.
        fail("TODO [특강 1 / 1-2, 2-1] 관계와 DB 제약을 검사하는 테스트를 작성하세요.");
    }

    @Test
    void requestAndJournalMustDescribeTheSameAmountAndAccounts() {
        // TODO [특강 1 / 4-2] 요청액 50000원, 분개 양쪽 40000원을 만들어 LedgerPostingService.post를 호출하세요.
        // 금액을 맞춘 뒤 고객 계좌만 다른 정상 계좌로 바꾼 사례도 각각 거절되는지 확인하세요.
        fail("TODO [특강 1 / 4-2] 요청 금액과 분개 금액 및 고객 계좌 검증을 작성하세요.");
    }
}
