package com.sparta.fintech.ledger.common.web;

import com.sparta.fintech.ledger.security.service.AuditLogService;
import com.sparta.fintech.ledger.transfer.service.IdempotencyKeyConflictException;
import com.sparta.fintech.ledger.transfer.service.TransferInProgressException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

@RestControllerAdvice
/** TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요. */
public class ApiExceptionHandler {
    private final AuditLogService audit;
    public ApiExceptionHandler(AuditLogService audit) { this.audit = audit; }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> authentication(AuthenticationException failure, HttpServletRequest request) {
        // TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.");
    }
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDenied(AccessDeniedException failure, HttpServletRequest request) {
        // TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.");
    }
    @ExceptionHandler({MethodArgumentNotValidException.class, HttpMessageNotReadableException.class,
        ServletRequestBindingException.class, org.springframework.web.method.annotation.MethodArgumentTypeMismatchException.class})
    public ResponseEntity<ErrorResponse> validation(Exception failure, HttpServletRequest request) {
        // TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.");
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> badRequest(IllegalArgumentException failure, HttpServletRequest request) {
        // TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.");
    }
    @ExceptionHandler({TransferInProgressException.class, IdempotencyKeyConflictException.class})
    public ResponseEntity<ErrorResponse> conflict(RuntimeException failure, HttpServletRequest request) {
        // TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.");
    }
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> status(ResponseStatusException failure, HttpServletRequest request) {
        // TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.");
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> system(Exception failure, HttpServletRequest request) {
        // TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.");
    }
    private ResponseEntity<ErrorResponse> error(int status, String code, String message, HttpServletRequest request) {
        // TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 6-1] 업무 오류, 권한 거절과 시스템 장애의 HTTP 상태 및 감사 기록을 연결하세요.");
    }
}
