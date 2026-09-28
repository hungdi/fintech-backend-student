package com.sparta.fintech.ledger.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.fintech.ledger.common.web.ErrorResponse;
import com.sparta.fintech.ledger.domain.AuditActionType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
/** TODO [특강 4 / 2-6] 제공자와 subject로 연결된 고객을 찾고 허용 및 거절 결과를 기록하세요. */
public class OAuth2JwtSuccessHandler implements AuthenticationSuccessHandler {
    private final AuthService auth;
    private final JwtTokenService tokens;
    private final AuditLogService audit;
    private final ObjectMapper mapper;

    public OAuth2JwtSuccessHandler(AuthService auth, JwtTokenService tokens, AuditLogService audit, ObjectMapper mapper) {
        this.auth = auth; this.tokens = tokens; this.audit = audit; this.mapper = mapper;
    }
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        // TODO [특강 4 / 2-6] 제공자와 subject로 연결된 고객을 찾고 허용 및 거절 결과를 기록하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-6] 제공자와 subject로 연결된 고객을 찾고 허용 및 거절 결과를 기록하세요.");
    }
}
