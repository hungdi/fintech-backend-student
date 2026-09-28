package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.CmCustomer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CmCustomerRepository extends JpaRepository<CmCustomer, Long> {

    Optional<CmCustomer> findByCustomerNo(String customerNo);
}
