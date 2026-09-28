package com.sparta.fintech.ledger.security.service;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
/** TODO [특강 4 / 4-1] 인증된 암호문 형식으로 암호화하고 변조되거나 지원하지 않는 형식은 거절하세요. */
public class SensitiveDataCryptoService {

    private final TextEncryptor textEncryptor;

    public SensitiveDataCryptoService(TextEncryptor textEncryptor) {
        this.textEncryptor = textEncryptor;
    }

    public String encrypt(String plainText) {
        // TODO [특강 4 / 4-1] 인증된 암호문 형식으로 암호화하고 변조되거나 지원하지 않는 형식은 거절하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-1] 인증된 암호문 형식으로 암호화하고 변조되거나 지원하지 않는 형식은 거절하세요.");
    }

    public String decrypt(String cipherText) {
        // TODO [특강 4 / 4-1] 인증된 암호문 형식으로 암호화하고 변조되거나 지원하지 않는 형식은 거절하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-1] 인증된 암호문 형식으로 암호화하고 변조되거나 지원하지 않는 형식은 거절하세요.");
    }
}
