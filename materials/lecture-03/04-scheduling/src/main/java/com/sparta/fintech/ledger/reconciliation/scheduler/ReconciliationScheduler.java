package com.sparta.fintech.ledger.reconciliation.scheduler;

import com.sparta.fintech.ledger.reconciliation.service.ReconciliationRunResult;
import com.sparta.fintech.ledger.reconciliation.service.ReconciliationService;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
/** TODO [특강 3 / 5-2 일별 마감 보완] 마감 잔액이 보관된 UTC 전날을 대사하세요. */
public class ReconciliationScheduler {

    private static final Logger log = LoggerFactory.getLogger(ReconciliationScheduler.class);
    private final ReconciliationService reconciliationService;
    private final Clock clock;

    public ReconciliationScheduler(
        ReconciliationService reconciliationService,
        Clock clock
    ) {
        this.reconciliationService = reconciliationService;
        this.clock = clock;
    }

    public void runDaily() {
        // TODO [특강 3 / 5-2 일별 마감 보완] reconciliation.schedule.cron과 UTC zone을 @Scheduled로 연결하세요.
        // Clock의 UTC 전날로 Service를 호출하고 실행 결과를 기록하세요. 마감 저장과 비교 로직은 분리합니다.
        throw new UnsupportedOperationException("TODO [특강 3 / 5-2 일별 마감 보완] UTC 전날의 대사 Service를 호출하세요.");
    }
}
