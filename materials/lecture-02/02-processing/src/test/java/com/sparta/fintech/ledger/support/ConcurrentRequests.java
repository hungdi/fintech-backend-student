package com.sparta.fintech.ledger.support;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.function.IntFunction;

/** 요청을 동시에 실행하고 모든 시도의 결과를 모읍니다. 금액과 기록 수는 호출한 테스트에서 검증하세요. */
public final class ConcurrentRequests {
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(20);
    private static final Duration TERMINATION_TIMEOUT = Duration.ofSeconds(20);

    private ConcurrentRequests() {}

    public record Attempt<T>(int index, T result, Exception error) {
        public boolean succeeded() { return error == null; }
    }

    /** index는 0부터 시작합니다. 요청의 업무 예외는 해당 Attempt에 보관합니다. */
    public static <T> List<Attempt<T>> run(int count, IntFunction<T> request) throws Exception {
        return run(count, request, REQUEST_TIMEOUT, TERMINATION_TIMEOUT);
    }

    /** 기존 호출 계약입니다. 모든 시도를 수집한 뒤 요청 예외가 있으면 첫 예외를 전달합니다. */
    public static <T> List<T> run(int count, Callable<T> request) throws Exception {
        Objects.requireNonNull(request, "request");
        List<Attempt<T>> attempts = execute(count, index -> request.call(), REQUEST_TIMEOUT, TERMINATION_TIMEOUT);
        List<T> results = new ArrayList<>();
        Exception failure = null;
        for (Attempt<T> attempt : attempts) {
            if (attempt.succeeded()) results.add(attempt.result());
            else if (failure == null) failure = attempt.error();
            else if (failure != attempt.error()) failure.addSuppressed(attempt.error());
        }
        if (failure != null) throw failure;
        return results;
    }

    // 실행 코드 자체의 단위테스트에서 제한 시간을 짧게 지정합니다.
    static <T> List<Attempt<T>> run(int count, IntFunction<T> request,
                                  Duration requestTimeout, Duration terminationTimeout) throws Exception {
        Objects.requireNonNull(request, "request");
        return execute(count, request::apply, requestTimeout, terminationTimeout);
    }

    private static <T> List<Attempt<T>> execute(int count, IndexedRequest<T> request,
                                               Duration requestTimeout, Duration terminationTimeout) throws Exception {
        if (count <= 0) throw new IllegalArgumentException("요청 수는 양수여야 합니다.");
        long requestNanos = positiveNanos(requestTimeout);
        long terminationNanos = positiveNanos(terminationTimeout);
        ExecutorService executor = Executors.newFixedThreadPool(count);
        TestDatabaseReset.trackConcurrentRequests(executor);
        CountDownLatch ready = new CountDownLatch(count);
        CountDownLatch start = new CountDownLatch(1);
        List<Future<Attempt<T>>> tasks = new ArrayList<>();
        Throwable failure = null;
        try {
            for (int i = 0; i < count; i++) {
                final int index = i;
                tasks.add(executor.submit(() -> {
                    ready.countDown();
                    start.await();
                    try {
                        return new Attempt<>(index, request.call(index), null);
                    } catch (InterruptedException interrupted) {
                        Thread.currentThread().interrupt();
                        throw interrupted;
                    } catch (Exception error) {
                        return new Attempt<T>(index, null, error);
                    }
                }));
            }
            if (!ready.await(requestNanos, TimeUnit.NANOSECONDS)) {
                throw new TimeoutException("동시 요청의 준비 시간 초과");
            }
            long deadline = System.nanoTime() + requestNanos;
            start.countDown();
            List<Attempt<T>> attempts = new ArrayList<>();
            for (Future<Attempt<T>> task : tasks) {
                // 개별 요청마다 시간을 다시 주지 않고 전체 결과 수집 시간을 제한합니다.
                attempts.add(task.get(Math.max(0, deadline - System.nanoTime()), TimeUnit.NANOSECONDS));
            }
            return List.copyOf(attempts);
        } catch (InterruptedException interrupted) {
            failure = interrupted;
            Thread.currentThread().interrupt();
            throw interrupted;
        } catch (Exception | Error error) {
            failure = error;
            throw error;
        } finally {
            for (Future<?> task : tasks) if (!task.isDone()) task.cancel(true);
            executor.shutdownNow();
            try {
                awaitTermination(executor, terminationNanos);
            } catch (Exception | Error cleanupFailure) {
                if (failure == null) throw cleanupFailure;
                failure.addSuppressed(cleanupFailure);
            }
        }
    }

    private static void awaitTermination(ExecutorService executor, long timeoutNanos) throws InterruptedException {
        boolean interrupted = Thread.interrupted();
        long deadline = System.nanoTime() + timeoutNanos;
        try {
            while (!executor.isTerminated()) {
                long remaining = deadline - System.nanoTime();
                if (remaining <= 0) {
                    throw new IllegalStateException("동시 요청 작업이 종료되지 않았습니다. DB 초기화가 차단됩니다.");
                }
                try {
                    executor.awaitTermination(remaining, TimeUnit.NANOSECONDS);
                } catch (InterruptedException ignored) {
                    interrupted = true;
                    executor.shutdownNow();
                }
            }
            if (interrupted) throw new InterruptedException("동시 요청 종료 대기 중 인터럽트가 발생했습니다.");
        } finally {
            if (interrupted) Thread.currentThread().interrupt();
        }
    }

    private static long positiveNanos(Duration timeout) {
        long nanos = Objects.requireNonNull(timeout, "timeout").toNanos();
        if (nanos <= 0) throw new IllegalArgumentException("제한 시간은 양수여야 합니다.");
        return nanos;
    }

    @FunctionalInterface
    private interface IndexedRequest<T> {
        T call(int index) throws Exception;
    }
}
