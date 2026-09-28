package com.sparta.fintech.ledger.transfer.web;

import com.sparta.fintech.ledger.transfer.service.TransferCommand;
import com.sparta.fintech.ledger.transfer.service.TransferResult;
import com.sparta.fintech.ledger.transfer.service.TransferService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
/** TODO [특강 2 / 6-6] Request와 요청 키를 Command로 바꾸고 Service 결과를 HTTP 응답으로 연결하세요. */
public class TransferController {

    private final TransferService transferService;

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping
    public ResponseEntity<TransferHttpResponse> transfer(
        @RequestHeader("Idempotency-Key") String idempotencyKey,
        @Valid @RequestBody TransferHttpRequest request
    ) {
        // TODO [특강 2 / 6-6] Request와 요청 키를 Command로 바꾸고 Service 결과를 HTTP 응답으로 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 6-6] Request와 요청 키를 Command로 바꾸고 Service 결과를 HTTP 응답으로 연결하세요.");
    }
}
