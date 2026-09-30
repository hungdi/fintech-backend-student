package com.sparta.fintech.ledger.support;

import java.time.Duration;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import javax.sql.DataSource;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/** DB와 송금 구현 없이 제공한 동시 실행 코드의 결과 수집과 종료를 확인합니다. */
@Tag("smoke")
@Timeout(10)
class ConcurrentRequestsTest {
    @Test
    void collectsEveryIndexedSuccessAndFailure() throws Exception {
        var calls = new AtomicInteger();
        var failure = new IllegalArgumentException("요청 거절");
        var attempts = ConcurrentRequests.run(4, index -> {
            calls.incrementAndGet();
            if (index == 1) throw failure;
            return index == 2 ? null : "key-" + index;
        });

        assertThat(calls.get()).isEqualTo(4);
        assertThat(attempts).extracting(ConcurrentRequests.Attempt::index).containsExactly(0, 1, 2, 3);
        assertThat(attempts.get(0).result()).isEqualTo("key-0");
        assertThat(attempts.get(1).error()).isSameAs(failure);
        assertThat(attempts.get(1).succeeded()).isFalse();
        assertThat(attempts.get(2).succeeded()).isTrue();
        assertThat(attempts.get(2).result()).isNull();
        assertThat(attempts.get(3).result()).isEqualTo("key-3");
        TestDatabaseReset.assertNoRunningRequests();
    }

    @Test
    void callableContractCollectsAllAttemptsBeforeThrowing() {
        var calls = new AtomicInteger();
        assertThrows(IllegalArgumentException.class, () -> ConcurrentRequests.run(4, () -> {
            if (calls.incrementAndGet() == 1) throw new IllegalArgumentException("첫 요청 거절");
            return "완료";
        }));
        assertThat(calls.get()).isEqualTo(4);
        TestDatabaseReset.assertNoRunningRequests();
    }

    @Test
    void timeoutCancelsAndWaitsForRunningRequest() {
        var exited = new CountDownLatch(1);
        assertThrows(TimeoutException.class, () -> ConcurrentRequests.run(1, index -> {
            try {
                new CountDownLatch(1).await();
            } catch (InterruptedException interrupted) {
                Thread.currentThread().interrupt();
            } finally {
                exited.countDown();
            }
            return index;
        }, Duration.ofSeconds(1), Duration.ofSeconds(2)));
        assertThat(exited.getCount()).isZero();
        TestDatabaseReset.assertNoRunningRequests();
    }

    @Test
    void callerInterruptionIsPropagatedAndRestoredAfterCleanup() {
        Thread.currentThread().interrupt();
        try {
            assertThrows(InterruptedException.class, () -> ConcurrentRequests.run(2, index -> index));
            assertThat(Thread.currentThread().isInterrupted()).isTrue();
            TestDatabaseReset.assertNoRunningRequests();
        } finally {
            Thread.interrupted();
        }
    }

    @Test
    void terminationFailurePreventsDatabaseResetUntilWorkerExits() throws Exception {
        var release = new CountDownLatch(1);
        var worker = new AtomicReference<Thread>();
        DataSource dataSource = mock(DataSource.class);
        try {
            var timeout = assertThrows(TimeoutException.class, () -> ConcurrentRequests.run(1, index -> {
                worker.set(Thread.currentThread());
                // 종료 요청에 응답하지 않는 작업을 재현하고, finally에서 반드시 해제합니다.
                while (release.getCount() != 0) {
                    try {
                        release.await();
                    } catch (InterruptedException ignored) {
                        // 이 테스트에서만 종료 실패를 재현하기 위해 인터럽트를 무시합니다.
                    }
                }
                return index;
            }, Duration.ofSeconds(1), Duration.ofMillis(100)));

            assertThat(timeout.getSuppressed()).hasSize(1);
            assertThat(timeout.getSuppressed()[0]).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("DB 초기화가 차단");
            assertThrows(IllegalStateException.class, () -> TestDatabaseReset.clear(dataSource));
            verifyNoInteractions(dataSource);
        } finally {
            release.countDown();
            if (worker.get() != null) {
                worker.get().join(TimeUnit.SECONDS.toMillis(2));
                assertThat(worker.get().isAlive()).isFalse();
            }
        }
        TestDatabaseReset.assertNoRunningRequests();
    }
}
