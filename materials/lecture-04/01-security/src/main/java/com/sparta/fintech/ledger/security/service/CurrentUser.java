package com.sparta.fintech.ledger.security.service;

import java.util.List;

public record CurrentUser(
    Long authUserId,
    Long customerId,
    String username,
    List<String> roles
) {
}
