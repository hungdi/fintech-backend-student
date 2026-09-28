package com.sparta.fintech.ledger.security.service;

import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
/** TODO [특강 4 / 4-1] 실습용 시크릿의 암호화와 복호화를 구현하고 암호문 형식 확인과 변조 검증을 구분하세요. */
public class SensitiveDataCryptoService {

    private final TextEncryptor textEncryptor;

    public SensitiveDataCryptoService(TextEncryptor textEncryptor) {
        this.textEncryptor = textEncryptor;
    }

    public String encrypt(String plainText) {
        // TODO [특강 4 / 4-1] TextEncryptor로 실습용 시크릿을 암호화하고 gcm:v1: 접두어를 붙이세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-1] TextEncryptor로 실습용 시크릿을 암호화하고 gcm:v1: 접두어를 붙이세요.");
    }

    public String decrypt(String cipherText) {
        // TODO [특강 4 / 4-1] gcm:v1: 접두어 확인과 복호화 중 변조 검증의 실패를 구분하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-1] gcm:v1: 접두어 확인과 복호화 중 변조 검증의 실패를 구분하세요.");
    }
}
