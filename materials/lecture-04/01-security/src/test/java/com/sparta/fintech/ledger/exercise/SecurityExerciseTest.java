package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.security.service.MaskingService;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("exercise")
class SecurityExerciseTest {
    @Test
    void masksAccountNumber() {
        assertThat(new MaskingService().accountNo("410-001")).isEqualTo("410-****");
    }
}
