package com.sparta.fintech.ledger.support;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/** 동시에 Service를 호출하는 준비 도우미입니다. 잔액이나 성공 여부는 호출한 테스트에서 검증하세요. */
public final class ConcurrentRequests {
    private ConcurrentRequests() {}
    public static <T> List<T> run(int count, Callable<T> request) throws Exception {
        var executor = Executors.newFixedThreadPool(count);
        var ready = new CountDownLatch(count);
        var start = new CountDownLatch(1);
        try {
            List<Future<T>> tasks = new ArrayList<>();
            for (int i = 0; i < count; i++) tasks.add(executor.submit(() -> {
                ready.countDown();
                if (!start.await(10, TimeUnit.SECONDS)) throw new IllegalStateException("시작 대기 시간 초과");
                return request.call();
            }));
            if (!ready.await(10, TimeUnit.SECONDS)) throw new IllegalStateException("작업 준비 시간 초과");
            start.countDown();
            List<T> results = new ArrayList<>();
            for (var task : tasks) results.add(task.get(20, TimeUnit.SECONDS));
            return results;
        } finally { executor.shutdownNow(); }
    }
}
