package com.sparta.fintech.ledger.transfer.web;

import com.sparta.fintech.ledger.transfer.service.IdempotencyKeyConflictException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
/** TODO [특강 2 / 6-4-1] 입력 오류와 잔액 부족, 요청 키 충돌에 맞는 표준 오류 응답을 연결하세요. */
public class TransferExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException exception) {
        // TODO [특강 2 / 6-4-1] 입력 오류와 잔액 부족, 요청 키 충돌에 맞는 표준 오류 응답을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-4-1] 입력 오류와 잔액 부족, 요청 키 충돌에 맞는 표준 오류 응답을 연결하세요.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException exception) {
        // TODO [특강 2 / 6-4-1] 입력 오류와 잔액 부족, 요청 키 충돌에 맞는 표준 오류 응답을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-4-1] 입력 오류와 잔액 부족, 요청 키 충돌에 맞는 표준 오류 응답을 연결하세요.");
    }

    @ExceptionHandler({IllegalStateException.class, IdempotencyKeyConflictException.class})
    public ResponseEntity<ErrorResponse> handleConflict(RuntimeException exception) {
        // TODO [특강 2 / 6-4-1] 입력 오류와 잔액 부족, 요청 키 충돌에 맞는 표준 오류 응답을 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-4-1] 입력 오류와 잔액 부족, 요청 키 충돌에 맞는 표준 오류 응답을 연결하세요.");
    }
}
