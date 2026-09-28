package com.sparta.fintech.ledger.security.service;

public record LoginCommand(
    String username,
    String password
) {
}
