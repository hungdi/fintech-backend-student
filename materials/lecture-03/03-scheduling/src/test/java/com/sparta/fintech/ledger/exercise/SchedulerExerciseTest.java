package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.reconciliation.scheduler.ReconciliationScheduler;
import com.sparta.fintech.ledger.reconciliation.service.ReconciliationRunResult;
import com.sparta.fintech.ledger.reconciliation.service.ReconciliationService;
import java.time.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

@Tag("exercise")
class SchedulerExerciseTest {
    @Test
    void passesUtcDateToReconciliation() {
        var service = mock(ReconciliationService.class);
        var date = LocalDate.of(2026, 9, 15);
        when(service.run(date)).thenReturn(new ReconciliationRunResult(1L, date, 12, 0));
        var scheduler = new ReconciliationScheduler(service,
            Clock.fixed(Instant.parse("2026-09-15T23:30:00Z"), ZoneOffset.UTC));
        scheduler.runDaily();
        verify(service).run(date);
        // TODO [특강 3 / 5-3] 기본 비활성 설정, UTC zone, 서비스 실패 전파를 추가 검증하세요.
        // 이 호출 테스트는 실제 시각에 cron이 발동했다는 뜻이 아닙니다.
    }
}
