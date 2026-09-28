package com.sparta.fintech.ledger.security.service;

public final class RequestTraceContext {
    private static final ThreadLocal<State> STATE = new ThreadLocal<>();
    private RequestTraceContext() {}
    public static void set(String traceId) { STATE.set(new State(traceId)); }
    public static String get() { return STATE.get() == null ? null : STATE.get().traceId; }
    public static boolean markAuditAttempt() {
        // TODO [특강 4 / 5-2] 같은 요청의 감사 기록 시도를 구분하고 중복 기록을 방지하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-2] 같은 요청의 감사 기록 시도를 구분하고 중복 기록을 방지하세요.");
    }
    public static void clear() { STATE.remove(); }
    private static final class State {
        final String traceId;
        boolean auditAttempted;
        State(String traceId) { this.traceId = traceId; }
    }
}
