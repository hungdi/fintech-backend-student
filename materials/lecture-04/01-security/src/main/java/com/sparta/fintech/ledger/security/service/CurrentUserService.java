package com.sparta.fintech.ledger.security.service;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

@Service
/** TODO [특강 4 / 2-4] 검증된 인증 객체에서 현재 사용자와 고객 정보를 읽으세요. */
public class CurrentUserService {

    public CurrentUser currentUser() {
        // TODO [특강 4 / 2-4] 검증된 인증 객체에서 현재 사용자와 고객 정보를 읽으세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-4] 검증된 인증 객체에서 현재 사용자와 고객 정보를 읽으세요.");
    }
}
