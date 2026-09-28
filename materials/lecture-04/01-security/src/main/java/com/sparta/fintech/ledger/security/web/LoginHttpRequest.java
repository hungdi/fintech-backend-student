package com.sparta.fintech.ledger.security.web;

import jakarta.validation.constraints.NotBlank;

/** TODO [특강 4 / 2-5] 로그인 이름과 비밀번호의 필수 입력 제약을 추가하세요. */
public record LoginHttpRequest(
    String username,
    String password
) {
}
