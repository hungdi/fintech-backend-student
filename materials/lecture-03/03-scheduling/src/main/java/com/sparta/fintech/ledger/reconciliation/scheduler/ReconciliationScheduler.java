package com.sparta.fintech.ledger.reconciliation.scheduler;

import com.sparta.fintech.ledger.reconciliation.service.ReconciliationRunResult;
import com.sparta.fintech.ledger.reconciliation.service.ReconciliationService;
import java.time.Clock;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
/** TODO [특강 3 / 5-2] UTC Clock의 업무일로 대사 Service를 호출하고 실행 결과를 기록하세요. */
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
        // TODO [특강 3 / 5-2] UTC Clock의 업무일로 대사 Service를 호출하고 실행 결과를 기록하세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 5-2] UTC Clock의 업무일로 대사 Service를 호출하고 실행 결과를 기록하세요.");
    }
}
