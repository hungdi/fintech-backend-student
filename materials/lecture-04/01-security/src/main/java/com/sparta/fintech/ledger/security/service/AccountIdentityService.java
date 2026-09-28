package com.sparta.fintech.ledger.security.service;

import com.sparta.fintech.ledger.domain.AmAuthUser;
import com.sparta.fintech.ledger.domain.CustomerStatus;
import com.sparta.fintech.ledger.repository.AmAuthUserRepository;
import com.sparta.fintech.ledger.repository.AmAuthUserRoleRepository;
import java.math.BigDecimal;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

/** TODO [특강 4 / 2-5-1] DB의 현재 계정과 고객 상태, 역할을 조회해 인증 주체를 구성하세요. */
public class AccountIdentityService {
    private final AmAuthUserRepository users;
    private final AmAuthUserRoleRepository roles;

    public AccountIdentityService(AmAuthUserRepository users, AmAuthUserRoleRepository roles) {
        this.users = users;
        this.roles = roles;
    }

    public CurrentUser fromJwt(Jwt jwt) {
        // TODO [특강 4 / 2-5-1] DB의 현재 계정과 고객 상태, 역할을 조회해 인증 주체를 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] DB의 현재 계정과 고객 상태, 역할을 조회해 인증 주체를 구성하세요.");
    }

    public CurrentUser forAccount(AmAuthUser user) {
        // TODO [특강 4 / 2-5-1] DB의 현재 계정과 고객 상태, 역할을 조회해 인증 주체를 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] DB의 현재 계정과 고객 상태, 역할을 조회해 인증 주체를 구성하세요.");
    }

    private Long idClaim(Jwt jwt, String name) {
        // TODO [특강 4 / 2-5-1] DB의 현재 계정과 고객 상태, 역할을 조회해 인증 주체를 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] DB의 현재 계정과 고객 상태, 역할을 조회해 인증 주체를 구성하세요.");
    }
}
