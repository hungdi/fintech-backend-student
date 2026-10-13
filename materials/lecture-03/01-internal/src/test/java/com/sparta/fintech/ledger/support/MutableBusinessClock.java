package com.sparta.fintech.ledger.support;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/** 날짜 경계 테스트에서 시간을 진행시키는 기술 코드입니다. 업무 날짜와 대사 규칙은 계산하지 않습니다. */
public final class MutableBusinessClock extends Clock {
    private final AtomicReference<Instant> current;
    private final ZoneId zone;

    public MutableBusinessClock(Instant initialTime) {
        this(new AtomicReference<>(Objects.requireNonNull(initialTime, "initialTime")), ZoneOffset.UTC);
    }

    private MutableBusinessClock(AtomicReference<Instant> current, ZoneId zone) {
        this.current = current;
        this.zone = Objects.requireNonNull(zone, "zone");
    }

    public void set(Instant time) {
        current.set(Objects.requireNonNull(time, "time"));
    }

    @Override
    public ZoneId getZone() {
        return zone;
    }

    @Override
    public Clock withZone(ZoneId zone) {
        return this.zone.equals(zone) ? this : new MutableBusinessClock(current, zone);
    }

    @Override
    public Instant instant() {
        return current.get();
    }
}
