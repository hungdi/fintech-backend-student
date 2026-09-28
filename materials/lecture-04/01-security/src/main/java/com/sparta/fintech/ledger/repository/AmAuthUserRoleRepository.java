package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.AmAuthUserRole;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AmAuthUserRoleRepository extends JpaRepository<AmAuthUserRole, Long> {

    List<AmAuthUserRole> findByAuthUserAuthUserId(Long authUserId);
}
