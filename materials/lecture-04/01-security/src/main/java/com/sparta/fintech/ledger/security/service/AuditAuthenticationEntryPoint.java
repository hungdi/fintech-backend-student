package com.sparta.fintech.ledger.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.fintech.ledger.common.web.ErrorResponse;
import com.sparta.fintech.ledger.domain.AuditActionType;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
/** TODO [특강 4 / 5-4] 인증 실패를 표준 응답과 감사 기록으로 연결하고 시스템 장애를 구분하세요. */
public class AuditAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final AuditLogService auditLogService;
    private final ObjectMapper objectMapper;

    public AuditAuthenticationEntryPoint(AuditLogService auditLogService, ObjectMapper objectMapper) {
        this.auditLogService = auditLogService;
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
        HttpServletRequest request,
        HttpServletResponse response,
        AuthenticationException authException
    ) throws IOException, ServletException {
        // TODO [특강 4 / 5-4] 인증 실패를 표준 응답과 감사 기록으로 연결하고 시스템 장애를 구분하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 5-4] 인증 실패를 표준 응답과 감사 기록으로 연결하고 시스템 장애를 구분하세요.");
    }
}
