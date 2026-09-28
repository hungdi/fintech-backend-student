package com.sparta.fintech.ledger.repository;

import com.sparta.fintech.ledger.domain.LmJournalEntry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LmJournalEntryRepository extends JpaRepository<LmJournalEntry, Long> {

    List<LmJournalEntry> findByGid(String gid);
}
