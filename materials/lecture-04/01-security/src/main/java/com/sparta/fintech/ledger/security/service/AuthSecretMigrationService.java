package com.sparta.fintech.ledger.security.service;

import com.sparta.fintech.ledger.repository.AmAuthUserRepository;
import com.sparta.fintech.ledger.security.config.SecurityProperties;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
/** TODO [특강 4 / 4-1] 검증된 기존 암호문만 새 형식으로 이관하고 재실행을 구분하세요. */
public class AuthSecretMigrationService {
    private final AmAuthUserRepository users;
    private final SensitiveDataCryptoService crypto;
    private final SecurityProperties properties;

    public AuthSecretMigrationService(AmAuthUserRepository users, SensitiveDataCryptoService crypto, SecurityProperties properties) {
        this.users = users;
        this.crypto = crypto;
        this.properties = properties;
    }

    public void migrateVerifiedLegacySecret(Long userId) {
        // TODO [특강 4 / 4-1] 검증된 기존 암호문만 새 형식으로 이관하고 재실행을 구분하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-1] 검증된 기존 암호문만 새 형식으로 이관하고 재실행을 구분하세요.");
    }
}
