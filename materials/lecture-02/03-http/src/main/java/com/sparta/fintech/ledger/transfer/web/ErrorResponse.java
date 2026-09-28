package com.sparta.fintech.ledger.transfer.web;

public record ErrorResponse(
    String code,
    String message
) {
}
