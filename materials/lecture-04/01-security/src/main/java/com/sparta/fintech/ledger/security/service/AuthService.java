package com.sparta.fintech.ledger.security.service;

import com.sparta.fintech.ledger.domain.AmAuthUser;
import com.sparta.fintech.ledger.domain.AuditActionType;
import com.sparta.fintech.ledger.repository.AmAuthUserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 4 / 2-2] LOCAL 비밀번호와 현재 계정 상태를 검증하고 토큰 및 감사 결과를 연결하세요. */
public class AuthService {

    private final AmAuthUserRepository authUserRepository;
    private final AccountIdentityService identities;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenService jwtTokenService;
    private final AuditLogService auditLogService;

    public AuthService(
        AmAuthUserRepository authUserRepository,
        AccountIdentityService identities,
        PasswordEncoder passwordEncoder,
        JwtTokenService jwtTokenService,
        AuditLogService auditLogService
    ) {
        this.authUserRepository = authUserRepository;
        this.identities = identities;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenService = jwtTokenService;
        this.auditLogService = auditLogService;
    }

    public LoginResult login(LoginCommand command) {
        // TODO [특강 4 / 2-2] LOCAL 비밀번호와 현재 계정 상태를 검증하고 토큰 및 감사 결과를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-2] LOCAL 비밀번호와 현재 계정 상태를 검증하고 토큰 및 감사 결과를 연결하세요.");
    }

    public CurrentUser oauth2Identity(String provider, String subject) {
        // TODO [특강 4 / 2-2] LOCAL 비밀번호와 현재 계정 상태를 검증하고 토큰 및 감사 결과를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-2] LOCAL 비밀번호와 현재 계정 상태를 검증하고 토큰 및 감사 결과를 연결하세요.");
    }

    public CurrentUser toCurrentUser(AmAuthUser authUser) {
        // TODO [특강 4 / 2-2] LOCAL 비밀번호와 현재 계정 상태를 검증하고 토큰 및 감사 결과를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-2] LOCAL 비밀번호와 현재 계정 상태를 검증하고 토큰 및 감사 결과를 연결하세요.");
    }
}
