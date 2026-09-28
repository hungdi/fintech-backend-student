package com.sparta.fintech.ledger.security.config;

import com.sparta.fintech.ledger.security.service.OAuth2JwtSuccessHandler;
import com.sparta.fintech.ledger.security.service.AuditAuthenticationEntryPoint;
import java.nio.charset.StandardCharsets;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.config.ObjectPostProcessor;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.nimbusds.jose.jwk.source.ImmutableSecret;

@Configuration
@EnableWebSecurity

@EnableConfigurationProperties(SecurityProperties.class)
/** TODO [특강 4 / 2-5-2] 로그인 공개 범위와 보호 API, JWT 검증 및 인증 실패 처리를 연결하세요. */
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity http,
        AuditAuthenticationEntryPoint authenticationEntryPoint,
        OAuth2JwtSuccessHandler oAuth2JwtSuccessHandler,
        com.sparta.fintech.ledger.security.service.AccountJwtAuthenticationConverter accountConverter,
        com.sparta.fintech.ledger.security.service.AuditLogService audit
    ) throws Exception {
        // TODO [특강 4 / 2-5-2] 로그인 공개 범위와 보호 API, JWT 검증 및 인증 실패 처리를 연결하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-2] 로그인 공개 범위와 보호 API, JWT 검증 및 인증 실패 처리를 연결하세요.");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // TODO [특강 4 / 2-2] 비밀번호 검증용 PasswordEncoder를 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-2] 비밀번호 검증용 PasswordEncoder를 구성하세요.");
    }

    @Bean
    public TextEncryptor textEncryptor(SecurityProperties properties) {
        // TODO [특강 4 / 4-1] 인증된 암호화를 지원하는 TextEncryptor를 설정하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 4-1] 인증된 암호화를 지원하는 TextEncryptor를 설정하세요.");
    }

    @Bean
    public SecretKey jwtSecretKey(SecurityProperties properties) {
        // TODO [특강 4 / 2-5-1] 바인딩한 설정에서 HS256 서명 키를 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] 바인딩한 설정에서 HS256 서명 키를 구성하세요.");
    }

    @Bean
    public JwtEncoder jwtEncoder(SecretKey jwtSecretKey) {
        // TODO [특강 4 / 2-5-1] 서명 키를 사용하는 JWT 인코더를 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] 서명 키를 사용하는 JWT 인코더를 구성하세요.");
    }

    @Bean
    public JwtDecoder jwtDecoder(SecretKey jwtSecretKey, SecurityProperties properties) {
        // TODO [특강 4 / 2-5-1] JWT 서명, 만료 시각과 발급자를 검증할 디코더를 구성하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] JWT 서명, 만료 시각과 발급자를 검증할 디코더를 구성하세요.");
    }

}
