package com.sparta.fintech.ledger.preparation;

import com.sparta.fintech.ledger.common.time.BusinessTimeConfiguration;
import com.sparta.fintech.ledger.domain.DebitCreditType;
import com.sparta.fintech.ledger.domain.TransactionType;
import com.sparta.fintech.ledger.service.LedgerPostingCommand;
import java.math.BigDecimal;
import java.time.ZoneOffset;
import java.util.List;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
class PreparationSmokeTest {
    @Test
    void businessClockUsesUtc() {
        assertThat(new BusinessTimeConfiguration().businessClock().getZone()).isEqualTo(ZoneOffset.UTC);
    }

    @Test
    void openingDepositDtoHasTraceIdsAndLedgerBalanceSnapshot() {
        var posting = new LedgerPostingCommand.AccountPosting(1L, DebitCreditType.CREDIT,
            new BigDecimal("100"), "입금", new BigDecimal("200"));
        var command = new LedgerPostingCommand("T-OPENING", "G-OPENING", null,
            TransactionType.DEPOSIT, new BigDecimal("100"), "KRW", "초기 입금",
            List.of(posting), List.of());
        assertThat(command.journalTid()).isEqualTo("T-OPENING-JOURNAL");
        assertThat(command.accountingLedgerTid()).isEqualTo("T-OPENING-LEDGER");
        assertThat(command.accountPostings()).containsExactly(posting);
        assertThat(posting.balanceAfter()).isEqualByComparingTo("200");
    }
}
