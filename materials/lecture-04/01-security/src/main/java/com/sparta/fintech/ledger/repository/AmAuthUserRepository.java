package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.AmAuthUser;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;

public interface AmAuthUserRepository extends JpaRepository<AmAuthUser, Long> {

    default Optional<AmAuthUser> findWithCustomerByAuthUserId(Long authUserId) {
        // TODO [특강 4 / 2-5-1] 조회 조건과 필요한 참조 로딩을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] 조회 조건과 필요한 참조 로딩을 구현하세요.");
    }

    default Optional<AmAuthUser> findByUsername(String username) {
        // TODO [특강 4 / 2-5-1] 조회 조건과 필요한 참조 로딩을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] 조회 조건과 필요한 참조 로딩을 구현하세요.");
    }

    default Optional<AmAuthUser> findByOauth2ProviderAndOauth2Subject(String oauth2Provider, String oauth2Subject) {
        // TODO [특강 4 / 2-5-1] 조회 조건과 필요한 참조 로딩을 구현하세요.
        throw new UnsupportedOperationException("TODO [특강 4 / 2-5-1] 조회 조건과 필요한 참조 로딩을 구현하세요.");
    }
}
