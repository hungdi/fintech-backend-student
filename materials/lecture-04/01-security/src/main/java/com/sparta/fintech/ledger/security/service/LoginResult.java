package com.sparta.fintech.ledger.security.service;

import java.util.List;

public record LoginResult(
    String accessToken,
    String tokenType,
    long expiresInSeconds,
    Long authUserId,
    Long customerId,
    String username,
    List<String> roles
) {
}
