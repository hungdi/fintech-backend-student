package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.support.SecurityTestSupport;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;

/** 상위 클래스의 초기화와 샘플 생성을 사용하고 필요한 Service와 조회 검증을 추가하세요. */
@Tag("exercise")
class SecurityIntegrationExerciseTest extends SecurityTestSupport {
    @Test
    void loginOwnershipRoleAndTokenFailures() {
        // TODO [특강 4 / 7-1, 7-2, 7-3] test와 security-test 프로필, MockMvc를 사용하세요.
        // 로그인 성공과 실패, 본인과 타인 계좌, 역할 부족, 만료와 변조 토큰을 각각 확인하세요.
        // 새 송금에서 출금 계좌 또는 소유 고객이 비활성일 때 거절과 실패 감사 기록도 검사하세요.
        // 본인 원장 조회의 항목별 JSON 키는 accountingLedgerItemId, ledgerAccountCode, ledgerAccountName,
        // maskedCustomerAccountNo, debitCreditType, amount, transactionDate의 7개와 정확히 같아야 합니다.
        // tid/gid/oid 및 maskedTid/maskedGid/maskedOid 키가 없고 계좌번호는 마스킹되는지 검사하세요.
        // 7개 필드의 값, 본인 조회 허용·타인 조회 거절·인증 및 역할 검사와 원장 조회 감사 기록을 확인하세요.
        // DB 원장과 내부 감사 기록의 TID/GID/OID는 원본 그대로 보존되는지도 검사하세요.
        fail("TODO [특강 4 / 7-1] 허용 및 거절 요청과 감사 로그를 검사하세요.");
    }
    @Test
    void idempotentReplayAndDurableCompletion() {
        // TODO [특강 4 / 8-2] 동일 키·내용 재시도, 같은 키·다른 내용 충돌과 동시에 다른 키의 송금을 검사하세요.
        // 정상 송금·재시도 전에 소유권을 확인하고, 완료 재시도에서는 계좌·고객 상태를 다시 검사하지 않습니다.
        // 재시도의 타인 소유 거절, 잔액 정합성 및 송금 성공·실패 감사 로그를 검사하세요.
        // 완료 기록 저장 실패 시 송금도 롤백하고 재시도 시 완료 기록은 한 건이어야 합니다.
        fail("TODO [특강 4 / 8-2] 재시도·동시성·감사 로그와 AhTransferCompletion의 송금 완료 기록을 확인하세요.");
    }
}
