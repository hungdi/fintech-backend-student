package com.sparta.fintech.ledger.security.service;

import org.springframework.core.convert.converter.Converter;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
/** TODO [특강 4 / 2-5-1] 검증된 JWT의 계정 식별값으로 현재 DB 상태와 역할을 재확인하세요. */
public class AccountJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final AccountIdentityService identities;

    public AccountJwtAuthenticationConverter(AccountIdentityService identities) {
        this.identities = identities;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        // TODO [특강 4 / 2-5-1] 검증된 JWT의 계정 식별값으로 현재 DB 상태와 역할을 재확인하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] 검증된 JWT의 계정 식별값으로 현재 DB 상태와 역할을 재확인하세요.");
    }
}
