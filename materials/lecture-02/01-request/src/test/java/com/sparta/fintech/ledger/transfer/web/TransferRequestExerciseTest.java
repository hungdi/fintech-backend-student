package com.sparta.fintech.ledger.transfer.web;

import jakarta.validation.Validation;
import java.math.BigDecimal;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("exercise")
class TransferRequestExerciseTest {
    @Test
    void amountMustFitStorageWithoutRounding() {
        // TODO [특강 2 / 1-3] 0원, 음수, 상한 초과, 불필요한 소수 끝자리 0도 입력해 기대 결과를 확인하세요.
        try (var factory = Validation.buildDefaultValidatorFactory()) {
            var request = new TransferHttpRequest("110-001", "110-002", new BigDecimal("0.005"), "KRW", "월세");
            assertThat(factory.getValidator().validate(request)).isNotEmpty();
        }
    }
}
