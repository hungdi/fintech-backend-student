package com.sparta.fintech.ledger.transfer.web;

import com.sparta.fintech.ledger.common.validation.ExactStoredAmount;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

/** TODO [특강 2 / 1-2] 필수 계좌번호, 양수 금액과 정확한 저장 범위를 선언하세요. */
public record TransferHttpRequest(
     String withdrawalAccountNo,
     String depositAccountNo,
       BigDecimal amount,
     String currencyCode,
    String description
) {
}
