package com.sparta.fintech.ledger.transfer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service

/** TODO [특강 2 / 5-5-2] 동일 키의 처리를 직렬화하고 송금 DB 커밋 뒤에 Lock이 해제되도록 경계를 정하세요. */
public class TransferService {

    private final IdempotencyKeyLockManager idempotencyKeyLockManager;
    private final TransferProcessor transferProcessor;

    public TransferService(
        IdempotencyKeyLockManager idempotencyKeyLockManager,
        TransferProcessor transferProcessor
    ) {
        this.idempotencyKeyLockManager = idempotencyKeyLockManager;
        this.transferProcessor = transferProcessor;
    }

    public TransferResult transfer(TransferCommand command) {
        // TODO [특강 2 / 5-5-2] 동일 키의 처리를 직렬화하고 송금 DB 커밋 뒤에 Lock이 해제되도록 경계를 정하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-5-2] 동일 키의 처리를 직렬화하고 송금 DB 커밋 뒤에 Lock이 해제되도록 경계를 정하세요.");
    }

    public TransferResult transfer(TransferCommand command, TransferPolicy policy) {
        // TODO [특강 2 / 5-5-2] 동일 키의 처리를 직렬화하고 송금 DB 커밋 뒤에 Lock이 해제되도록 경계를 정하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-5-2] 동일 키의 처리를 직렬화하고 송금 DB 커밋 뒤에 Lock이 해제되도록 경계를 정하세요.");
    }

    private void requireText(String value, String name) {
        // TODO [특강 2 / 5-5-2] 동일 키의 처리를 직렬화하고 송금 DB 커밋 뒤에 Lock이 해제되도록 경계를 정하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-5-2] 동일 키의 처리를 직렬화하고 송금 DB 커밋 뒤에 Lock이 해제되도록 경계를 정하세요.");
    }
}
