package com.sparta.fintech.ledger.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.security")
public record SecurityProperties(
    String issuer,
    long accessTokenTtlMinutes,
    String jwtSecret,
    String cryptoPassword,
    String cryptoSalt
) {
    public SecurityProperties {
        validate(issuer, accessTokenTtlMinutes, jwtSecret, cryptoPassword, cryptoSalt);
    }
    private static void validate(String issuer, long accessTokenTtlMinutes, String jwtSecret,
        String cryptoPassword, String cryptoSalt) {
        // TODO [특강 4 / 2-5-1] 설정 기본값과 JWT 키 길이, 암호화 비밀값의 필수 조건을 검증하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] 설정 기본값과 JWT 키 길이, 암호화 비밀값의 필수 조건을 검증하세요.");
    }
}
