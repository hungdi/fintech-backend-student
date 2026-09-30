package com.sparta.fintech.ledger.reconciliation.scheduler;

import com.sparta.fintech.ledger.reconciliation.service.DailyClosingService;
import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneOffset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/** TODO [특강 3 / 5장 일별 마감 보완] 마감 잔액 보관 예약을 대사 예약과 분리하세요. */
@Component
public class DailyClosingScheduler {

    private static final Logger log = LoggerFactory.getLogger(DailyClosingScheduler.class);
    private final DailyClosingService dailyClosingService;
    private final Clock clock;

    public DailyClosingScheduler(DailyClosingService dailyClosingService, Clock clock) {
        this.dailyClosingService = dailyClosingService;
        this.clock = clock;
    }

    // TODO [특강 3 / 5장 일별 마감 보완] reconciliation.closing.schedule.cron 설정과 UTC zone을 @Scheduled로 연결하세요.
    public void runDaily() {
        // UTC 전날을 capture에 전달하고 시작/완료/실패 로그를 기록합니다. 실패를 삼키지 마세요.
        throw new UnsupportedOperationException("TODO [특강 3 / 5장 일별 마감 보완] UTC 전날의 마감 잔액 보관을 예약하세요.");
    }
}
