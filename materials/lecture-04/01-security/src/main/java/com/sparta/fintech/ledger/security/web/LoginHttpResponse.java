package com.sparta.fintech.ledger.security.web;

import com.sparta.fintech.ledger.security.service.LoginResult;
import java.util.List;

public record LoginHttpResponse(
    String accessToken,
    String tokenType,
    long expiresInSeconds,
    Long authUserId,
    Long customerId,
    String username,
    List<String> roles
) {

    public static LoginHttpResponse from(LoginResult result) {
        return new LoginHttpResponse(
            result.accessToken(),
            result.tokenType(),
            result.expiresInSeconds(),
            result.authUserId(),
            result.customerId(),
            result.username(),
            result.roles()
        );
    }
}
