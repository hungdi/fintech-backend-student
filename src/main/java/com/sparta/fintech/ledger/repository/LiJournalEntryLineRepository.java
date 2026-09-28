package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.LiJournalEntryLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LiJournalEntryLineRepository extends JpaRepository<LiJournalEntryLine, Long> {
}
