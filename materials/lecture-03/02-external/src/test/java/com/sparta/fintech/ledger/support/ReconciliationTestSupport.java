package com.sparta.fintech.ledger.support;

import com.sparta.fintech.ledger.domain.DmTransferOrder;
import java.math.BigDecimal;

/** 내부 대사의 고정 시각·마감 입력을 공유하고 외부 비교용 완료 송금 오더를 추가합니다. */
public abstract class ReconciliationTestSupport extends InternalReconciliationTestSupport {
    @Override
    protected ReconciliationSampleData createSampleData() {
        // 3-2 확장의 마감 엔티티·업무일 과제를 완성한 뒤 내부 샘플을 준비하세요.
        ReconciliationSampleData sample = super.createSampleData();
        var from = accountRepository.findById(sample.withdrawalAccountId()).orElseThrow();
        var to = accountRepository.findById(sample.depositAccountId()).orElseThrow();
        var transfer = transactionRepository.findById(sample.transferTransactionId()).orElseThrow();
        var journal = journalEntryRepository.findById(sample.transferJournalEntryId()).orElseThrow();
        DmTransferOrder order = new DmTransferOrder(
            "settlement-key", "request-hash", "T-TRANSFER", "G-TRANSFER",
            "T-TRANSFER-J", "T-TRANSFER-L", from, to, new BigDecimal("300"), "KRW", "OTHER-BANK"
        );
        order.complete(transfer.getTransactionId(), journal.getJournalEntryId(), journal.getVoucherNo(), POSTED_AT);
        transferOrderRepository.save(order);
        return sample;
    }
}
