package com.sparta.fintech.ledger.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

/** TODO [특강 4 / 1-2-1] 금융 고객과 인증 계정 관계 및 LOCAL과 외부 제공자 식별 제약을 구현하세요. 필드와 생성자, getter는 호출 계약으로 제공합니다. */
public class AmAuthUser extends BaseTimeEntity {

    private Long authUserId;

    private CmCustomer customer;

    private String username;

    private String passwordHash;

    private AuthProviderType authProviderType;

    private String oauth2Provider;

    private String oauth2Subject;

    private String encryptedAuthSecret;

    private boolean enabled;

    protected AmAuthUser() {
    }

    public AmAuthUser(CmCustomer customer, String username, String passwordHash, String encryptedAuthSecret) {
        this.customer = customer;
        this.username = username;
        this.passwordHash = passwordHash;
        this.encryptedAuthSecret = encryptedAuthSecret;
        this.authProviderType = AuthProviderType.LOCAL;
        this.enabled = true;
    }

    public static AmAuthUser oauth2(
        CmCustomer customer,
        String username,
        String oauth2Provider,
        String oauth2Subject,
        String encryptedAuthSecret
    ) {
        // TODO [특강 4 / 1-2-1] 금융 고객과 인증 계정 관계 및 LOCAL과 외부 제공자 식별 제약을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 1-2-1] 금융 고객과 인증 계정 관계 및 LOCAL과 외부 제공자 식별 제약을 구현하세요.");
    }

    public void replaceEncryptedAuthSecret(String cipherText) {
        // TODO [특강 4 / 1-2-1] 금융 고객과 인증 계정 관계 및 LOCAL과 외부 제공자 식별 제약을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 1-2-1] 금융 고객과 인증 계정 관계 및 LOCAL과 외부 제공자 식별 제약을 구현하세요.");
    }

    public void disable() {
        // TODO [특강 4 / 1-2-1] 금융 고객과 인증 계정 관계 및 LOCAL과 외부 제공자 식별 제약을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 1-2-1] 금융 고객과 인증 계정 관계 및 LOCAL과 외부 제공자 식별 제약을 구현하세요.");
    }

    public Long getAuthUserId() {
        return authUserId;
    }

    public CmCustomer getCustomer() {
        return customer;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public AuthProviderType getAuthProviderType() {
        return authProviderType;
    }

    public String getOauth2Provider() {
        return oauth2Provider;
    }

    public String getOauth2Subject() {
        return oauth2Subject;
    }

    public String getEncryptedAuthSecret() {
        return encryptedAuthSecret;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
