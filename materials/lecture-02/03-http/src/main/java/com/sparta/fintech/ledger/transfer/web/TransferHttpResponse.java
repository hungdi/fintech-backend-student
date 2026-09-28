package com.sparta.fintech.ledger.transfer.web;

import com.sparta.fintech.ledger.domain.TransferOrderStatus;
import com.sparta.fintech.ledger.transfer.service.TransferResult;
import java.math.BigDecimal;

public record TransferHttpResponse(
    Long transferOrderId,
    String tid,
    TransferOrderStatus status,
    BigDecimal withdrawalAccountBalance
) {
    public static TransferHttpResponse from(TransferResult result) {
        return new TransferHttpResponse(result.transferOrderId(), result.tid(),
            result.status(), result.withdrawalAccountBalance());
    }
}
