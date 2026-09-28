package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.security.service.MaskingService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@Tag("exercise")
class SecurityExerciseTest {
    @Test
    void masksAccountNumber() {
        assertThat(new MaskingService().accountNo("410-001")).isEqualTo("410-****");
    }
    @Test
    void loginOwnershipRoleAndTokenFailures() {
        // TODO [특강 4 / 7-1, 7-2, 7-3] test와 security-test 프로필, MockMvc를 사용하세요.
        // 로그인 성공과 실패, 본인과 타인 계좌, 역할 부족, 만료와 변조 토큰을 각각 확인하세요.
        fail("TODO [특강 4 / 7-1] 허용 및 거절 요청과 감사 로그를 검사하세요.");
    }
    @Test
    void dailyLimitReplayAndDurableCompletion() {
        // TODO [특강 4 / 8-2] 누적 한도, 동일 요청 재시도와 동시에 다른 키의 송금을 검사하세요.
        // 완료 기록 저장 실패 시 송금도 롤백하고 재시도 시 완료 기록은 한 건이어야 합니다.
        fail("TODO [특강 4 / 8-2] 일일 한도와 AhTransferCompletion의 송금 완료 기록을 확인하세요.");
    }
}
