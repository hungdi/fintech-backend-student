package com.sparta.fintech.ledger.ledger.web;

import com.sparta.fintech.ledger.ledger.service.SecureLedgerQueryService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
/** TODO [특강 4 / 4-3] 고객 역할과 원장 조회 Service를 연결하세요. */
public class LedgerQueryController {

    private final SecureLedgerQueryService secureLedgerQueryService;

    public LedgerQueryController(SecureLedgerQueryService secureLedgerQueryService) {
        this.secureLedgerQueryService = secureLedgerQueryService;
    }

    @GetMapping("/{accountNo}/ledger-items")

    public ResponseEntity<List<MaskedLedgerItemResponse>> getMyLedgerItems(@PathVariable String accountNo) {
        // TODO [특강 4 / 4-3] 고객 역할과 원장 조회 Service를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-3] 고객 역할과 원장 조회 Service를 연결하세요.");
    }
}
