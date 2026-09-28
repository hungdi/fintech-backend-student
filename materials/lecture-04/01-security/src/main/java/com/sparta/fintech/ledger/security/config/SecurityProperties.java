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
        // TODO [특강 4 / 2-5-1] 설정 기본값과 JWT 서명 키 jwtSecret의 길이, 암호화 키 생성에 쓰는 cryptoPassword와 cryptoSalt의 필수 조건을 검증하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] 설정 기본값과 JWT 서명 키 jwtSecret의 길이, 암호화 키 생성에 쓰는 cryptoPassword와 cryptoSalt의 필수 조건을 검증하세요.");
    }
}
