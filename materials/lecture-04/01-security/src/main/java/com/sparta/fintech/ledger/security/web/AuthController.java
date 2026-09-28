package com.sparta.fintech.ledger.security.web;

import com.sparta.fintech.ledger.security.service.AuthService;
import com.sparta.fintech.ledger.security.service.LoginCommand;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
/** TODO [특강 4 / 2-5] 로그인 요청을 인증 Service와 결과 DTO로 연결하세요. */
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginHttpResponse> login(@Valid @RequestBody LoginHttpRequest request) {
        // TODO [특강 4 / 2-5] 로그인 요청을 인증 Service와 결과 DTO로 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5] 로그인 요청을 인증 Service와 결과 DTO로 연결하세요.");
    }
}
