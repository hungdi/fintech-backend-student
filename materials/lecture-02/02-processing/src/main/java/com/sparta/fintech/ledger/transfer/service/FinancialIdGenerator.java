package com.sparta.fintech.ledger.transfer.service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class FinancialIdGenerator {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");

    private final Clock clock;

    public FinancialIdGenerator(Clock clock) {
        this.clock = clock;
    }

    public GeneratedTransferTraceIds generate() {
        String gid = next("G");
        String transferTid = next("T");
        return new GeneratedTransferTraceIds(
            transferTid,
            gid,
            next("T"),
            next("T")
        );
    }

    private String next(String prefix) {
        String timePart = LocalDateTime.now(clock).format(FORMATTER);
        String randomPart = UUID.randomUUID().toString().replace("-", "").substring(0, 8).toUpperCase();
        return prefix + timePart + randomPart;
    }
}
