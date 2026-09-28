package com.sparta.fintech.ledger.transfer.service;

import com.sparta.fintech.ledger.domain.DmAccount;
import java.time.LocalDate;

@FunctionalInterface
public interface TransferPolicy {
    TransferPolicy NONE = (account, command, businessDate, replay) -> {};

    void validate(DmAccount account, TransferCommand command, LocalDate businessDate, boolean replay);
}
