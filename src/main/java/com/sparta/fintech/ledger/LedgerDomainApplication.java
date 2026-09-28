package com.sparta.fintech.ledger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(dateTimeProviderRef = "auditingDateTimeProvider")
@SpringBootApplication
public class LedgerDomainApplication {

    public static void main(String[] args) {
        SpringApplication.run(LedgerDomainApplication.class, args);
    }
}
