package com.sparta.fintech.ledger.common.web;

public record ErrorResponse(
    String code,
    String message
) {
}
