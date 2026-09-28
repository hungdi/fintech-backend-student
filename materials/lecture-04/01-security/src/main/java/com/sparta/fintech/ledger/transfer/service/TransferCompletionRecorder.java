package com.sparta.fintech.ledger.transfer.service;

import com.sparta.fintech.ledger.domain.AhTransferCompletion;
import com.sparta.fintech.ledger.domain.DmTransferOrder;
import com.sparta.fintech.ledger.repository.AhTransferCompletionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 4 / 5-3-2] 송금 트랜잭션 안에서 완료 증거를 저장하고 기록 실패 시 송금도 롤백하세요. */
public class TransferCompletionRecorder {
    private final AhTransferCompletionRepository repository;
    public TransferCompletionRecorder(AhTransferCompletionRepository repository) { this.repository = repository; }

    public void record(DmTransferOrder order) { repository.saveAndFlush(new AhTransferCompletion(order)); }
}
