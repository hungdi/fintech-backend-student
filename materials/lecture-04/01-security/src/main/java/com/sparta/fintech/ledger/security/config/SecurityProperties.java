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
        // 기본값은 record 필드에 저장되기 전에 생성자에서 적용합니다.
        if (issuer == null || issuer.isBlank()) {
            issuer = "sparta-fintech";
        }
        if (accessTokenTtlMinutes <= 0) {
            accessTokenTtlMinutes = 30;
        }
        validate(issuer, accessTokenTtlMinutes, jwtSecret, cryptoPassword, cryptoSalt);
    }
    private static void validate(String issuer, long accessTokenTtlMinutes, String jwtSecret,
        String cryptoPassword, String cryptoSalt) {
        // TODO [특강 4 / 2-5-1] jwtSecret이 UTF-8 기준 32바이트 이상인지, cryptoPassword와 cryptoSalt가 비어 있지 않은지 검증하세요. 기본값은 생성자에서 이미 적용됩니다.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] JWT 서명 키의 UTF-8 바이트 길이와 암호화 설정의 필수 조건을 검증하세요.");
    }
}
