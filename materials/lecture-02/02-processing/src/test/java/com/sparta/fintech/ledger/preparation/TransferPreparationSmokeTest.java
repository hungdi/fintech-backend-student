package com.sparta.fintech.ledger.preparation;

import com.sparta.fintech.ledger.transfer.service.FinancialIdGenerator;
import com.sparta.fintech.ledger.domain.DmTransferOrder;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.HashSet;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
class TransferPreparationSmokeTest {
    @Test
    void generatedTraceIdsUseInjectedClock() {
        var ids = new FinancialIdGenerator(Clock.fixed(Instant.parse("2026-09-15T12:00:00Z"), ZoneOffset.UTC)).generate();
        var values = List.of(ids.tid(), ids.gid(), ids.journalTid(), ids.accountingLedgerTid());
        assertThat(new HashSet<>(values)).hasSize(4);
        assertThat(values).allSatisfy(value -> assertThat(value).contains("20260915120000000"));
    }
    @Test
    void completionSignaturesAreAvailableBeforeReconciliation() throws Exception {
        assertThat(DmTransferOrder.class.getMethod("getCompletedAt").getReturnType()).isEqualTo(Instant.class);
        assertThat(DmTransferOrder.class.getMethod("complete", Long.class, Long.class, String.class)).isNotNull();
        assertThat(DmTransferOrder.class.getMethod("complete", Long.class, Long.class, String.class, Instant.class)).isNotNull();
    }
}
