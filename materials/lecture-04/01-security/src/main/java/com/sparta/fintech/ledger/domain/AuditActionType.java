package com.sparta.fintech.ledger.domain;

public enum AuditActionType {

    LOGIN,

    OAUTH2_LOGIN,

    AUTHENTICATION_REQUIRED,

    ACCOUNT_READ,

    LEDGER_READ,

    TRANSFER_REQUEST,

    HTTP_REQUEST,

    RECONCILIATION_READ
}
