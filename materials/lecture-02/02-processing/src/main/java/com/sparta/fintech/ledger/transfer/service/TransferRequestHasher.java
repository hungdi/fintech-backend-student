package com.sparta.fintech.ledger.transfer.service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
/** TODO [특강 2 / 5-3] 금액 표기와 null 메모를 정규화하고 필드 길이를 포함한 요청 해시를 만드세요. */
public class TransferRequestHasher {

    public String hash(TransferCommand command) {
        // TODO [특강 2 / 5-3] 금액 표기와 null 메모를 정규화하고 필드 길이를 포함한 요청 해시를 만드세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-3] 금액 표기와 null 메모를 정규화하고 필드 길이를 포함한 요청 해시를 만드세요.");
    }

    private String normalize(BigDecimal amount) {
        // TODO [특강 2 / 5-3] 금액 표기와 null 메모를 정규화하고 필드 길이를 포함한 요청 해시를 만드세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-3] 금액 표기와 null 메모를 정규화하고 필드 길이를 포함한 요청 해시를 만드세요.");
    }

    private String sha256(String value) {
        // TODO [특강 2 / 5-3] 금액 표기와 null 메모를 정규화하고 필드 길이를 포함한 요청 해시를 만드세요.
        throw new UnsupportedOperationException("TODO [특강 2 / 5-3] 금액 표기와 null 메모를 정규화하고 필드 길이를 포함한 요청 해시를 만드세요.");
    }
}
