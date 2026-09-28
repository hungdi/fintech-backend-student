package com.sparta.fintech.ledger.account.web;

import com.sparta.fintech.ledger.account.service.SecureAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
/** TODO [특강 4 / 3-1] 고객 역할과 본인 계좌 조회 Service를 연결하세요. */
public class AccountController {

    private final SecureAccountService secureAccountService;

    public AccountController(SecureAccountService secureAccountService) {
        this.secureAccountService = secureAccountService;
    }

    @GetMapping("/{accountNo}")

    public ResponseEntity<AccountHttpResponse> getMyAccount(@PathVariable String accountNo) {
        // TODO [특강 4 / 3-1] 고객 역할과 본인 계좌 조회 Service를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 3-1] 고객 역할과 본인 계좌 조회 Service를 연결하세요.");
    }
}
