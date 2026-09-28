package com.sparta.fintech.ledger.transfer.service;

import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;
import org.springframework.stereotype.Component;

@Component
/** TODO [특강 2 / 5-5-1] 고정 스트라이프 Lock으로 같은 키를 순서대로 처리하고 예외가 나도 해제하세요. */
public class IdempotencyKeyLockManager {

    private static final int STRIPE_COUNT = 1024;
    private final ReentrantLock[] locks = new ReentrantLock[STRIPE_COUNT];

    public IdempotencyKeyLockManager() {
        for (int i = 0; i < locks.length; i++) {
            locks[i] = new ReentrantLock();
        }
    }

    public <T> T execute(String idempotencyKey, Supplier<T> action) {
        // TODO [특강 2 / 5-5-1] 고정 스트라이프 Lock으로 같은 키를 순서대로 처리하고 예외가 나도 해제하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-5-1] 고정 스트라이프 Lock으로 같은 키를 순서대로 처리하고 예외가 나도 해제하세요.");
    }
}
