package com.sparta.fintech.ledger.security.service;

import com.sparta.fintech.ledger.security.config.SecurityProperties;
import java.time.Instant;
import java.time.Clock;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
/** TODO [특강 4 / 2-3] 발급 시점의 고객과 역할 정보 Claim, 발급자 및 UTC 만료 시각을 넣어 JWT를 발급하세요. */
public class JwtTokenService {

    private final Clock clock;
    private final JwtEncoder jwtEncoder;
    private final SecurityProperties securityProperties;

    public JwtTokenService(JwtEncoder jwtEncoder, SecurityProperties securityProperties, Clock clock) {
        this.clock = clock;
        this.jwtEncoder = jwtEncoder;
        this.securityProperties = securityProperties;
    }

    // API 인가는 AccountJwtAuthenticationConverter가 조회한 현재 DB 역할을 사용합니다.
    public String issue(CurrentUser currentUser) {
        // TODO [특강 4 / 2-3] 발급 시점의 고객과 역할 정보 Claim, 발급자 및 UTC 만료 시각을 넣어 JWT를 발급하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-3] 발급 시점의 고객과 역할 정보 Claim, 발급자 및 UTC 만료 시각을 넣어 JWT를 발급하세요.");
    }

    public long expiresInSeconds() {
        // TODO [특강 4 / 2-3] 발급 시점의 고객과 역할 정보 Claim, 발급자 및 UTC 만료 시각을 넣어 JWT를 발급하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-3] 발급 시점의 고객과 역할 정보 Claim, 발급자 및 UTC 만료 시각을 넣어 JWT를 발급하세요.");
    }
}
