package com.sparta.fintech.ledger.exercise;

import com.sparta.fintech.ledger.reconciliation.scheduler.DailyClosingScheduler;
import com.sparta.fintech.ledger.reconciliation.scheduler.ReconciliationScheduler;
import com.sparta.fintech.ledger.reconciliation.service.DailyClosingService;
import com.sparta.fintech.ledger.reconciliation.service.ReconciliationRunResult;
import com.sparta.fintech.ledger.reconciliation.service.ReconciliationService;
import java.time.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@Tag("exercise")
class SchedulerExerciseTest {
    @Test
    void passesPreviousUtcDateToReconciliation() {
        var service = mock(ReconciliationService.class);
        var closedDate = LocalDate.of(2026, 9, 15);
        when(service.run(closedDate)).thenReturn(new ReconciliationRunResult(1L, closedDate, 2, 0));
        var scheduler = new ReconciliationScheduler(service,
            Clock.fixed(Instant.parse("2026-09-16T02:00:00Z"), ZoneOffset.UTC));
        scheduler.runDaily();
        verify(service).run(closedDate);
        verifyNoMoreInteractions(service);
        // 이 호출 테스트는 실제 시각에 cron이 발동했다는 뜻이 아닙니다.
    }

    @Test
    void passesPreviousUtcDateToClosing() {
        var service = mock(DailyClosingService.class);
        var closedDate = LocalDate.of(2026, 9, 15);
        when(service.capture(closedDate)).thenReturn(2);
        var scheduler = new DailyClosingScheduler(service,
            Clock.fixed(Instant.parse("2026-09-16T00:10:00Z"), ZoneId.of("Pacific/Honolulu")));
        scheduler.runDaily();
        verify(service).capture(closedDate);
        verifyNoMoreInteractions(service);
        // Clock의 표시 시간대와 관계없이 UTC 전날을 선택해야 합니다.
    }

    @Test
    void propagatesFailuresFromBothBatchServices() {
        var closingService = mock(DailyClosingService.class);
        var reconciliationService = mock(ReconciliationService.class);
        var closedDate = LocalDate.of(2026, 9, 15);
        var fixedClock = Clock.fixed(Instant.parse("2026-09-16T02:00:00Z"), ZoneOffset.UTC);
        var failure = new IllegalStateException("배치 저장 실패 재현");
        when(closingService.capture(closedDate)).thenThrow(failure);
        when(reconciliationService.run(closedDate)).thenThrow(failure);
        assertThatThrownBy(() -> new DailyClosingScheduler(closingService, fixedClock).runDaily()).isSameAs(failure);
        assertThatThrownBy(() -> new ReconciliationScheduler(reconciliationService, fixedClock).runDaily()).isSameAs(failure);
    }

    @Test
    void schedulingIsDisabledByDefaultAndUsesSeparateUtcCrons() {
        // TODO [특강 3 / 5-3] Spring 컨텍스트에서 두 예약의 기본 cron이 '-'이고 등록된 작업이 없는지 확인하세요.
        // batch 프로필은 reconciliation.closing.schedule.cron=0 10 0 * * * 과
        // reconciliation.schedule.cron=0 0 2 * * * 을 각각 등록해야 합니다. 두 Trigger의 시간대는 UTC입니다.
        // 고정 Clock으로 다음 실행 시각을 조회하고 등록된 Runnable을 호출해 각 Service의 전날 인자를 확인하세요.
        // 실제 시간을 기다리는 sleep이나 운영 DB 실행으로 이 테스트를 대신하지 않습니다.
        fail("TODO [특강 3 / 5-3] 마감과 대사의 별도 UTC 예약 및 기본 비활성화를 검사하세요.");
    }

    @Test
    void eitherBatchCanBeDisabledWithoutDisablingTheOther() {
        // TODO [특강 3 / 5-3] batch 프로필에서 마감 cron만 '-'로 바꾸면 대사 작업 한 개가 유지되어야 합니다.
        // 대사 cron만 '-'로 바꾸면 마감 작업 한 개가 유지되어야 합니다. 환경 변수 override도 별도로 확인하세요.
        // DAILY_CLOSING_CRON은 마감, RECONCILIATION_CRON은 대사의 설정입니다.
        fail("TODO [특강 3 / 5-3] 한 배치의 비활성화가 다른 배치의 예약을 바꾸지 않는지 확인하세요.");
    }
}
