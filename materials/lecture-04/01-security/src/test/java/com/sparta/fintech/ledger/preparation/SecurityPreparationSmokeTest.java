package com.sparta.fintech.ledger.preparation;

import com.sparta.fintech.ledger.security.config.SecurityConfig;
import com.sparta.fintech.ledger.security.config.SecurityProperties;
import java.util.Arrays;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("smoke")
class SecurityPreparationSmokeTest {
    @Test
    void securityPropertiesBindingDeclarationIsConnected() {
        assertThat(SecurityProperties.class.getAnnotation(ConfigurationProperties.class).prefix()).isEqualTo("app.security");
        assertThat(Arrays.stream(SecurityProperties.class.getRecordComponents()).map(c -> c.getName()))
            .containsExactly("issuer", "accessTokenTtlMinutes", "jwtSecret", "cryptoPassword", "cryptoSalt");
        assertThat(SecurityConfig.class.getAnnotation(EnableConfigurationProperties.class).value())
            .contains(SecurityProperties.class);
        // 실제 설정 바인딩과 값 검증은 SecurityProperties 과제를 완성한 뒤 따로 확인하세요.
    }
}
