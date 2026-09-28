package com.sparta.fintech.ledger.security.service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;
import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
/** TODO [특강 4 / 5-1] 요청 추적 ID를 응답 및 처리 문맥에 연결하고 요청이 끝나면 정리하세요. */
public class RequestTraceIdFilter extends OncePerRequestFilter {

    public static final String HEADER_NAME = "X-Request-Trace-Id";

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        // TODO [특강 4 / 5-1] 요청 추적 ID를 응답 및 처리 문맥에 연결하고 요청이 끝나면 정리하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-1] 요청 추적 ID를 응답 및 처리 문맥에 연결하고 요청이 끝나면 정리하세요.");
    }

    private String traceId(HttpServletRequest request) {
        // TODO [특강 4 / 5-1] 요청 추적 ID를 응답 및 처리 문맥에 연결하고 요청이 끝나면 정리하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-1] 요청 추적 ID를 응답 및 처리 문맥에 연결하고 요청이 끝나면 정리하세요.");
    }
}
