package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.AcAuthRole;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AcAuthRoleRepository extends JpaRepository<AcAuthRole, Long> {

    Optional<AcAuthRole> findByRoleCode(String roleCode);
}
