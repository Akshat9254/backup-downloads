package com.edigest.journal.service;

import com.edigest.journal.entity.Journal;
import com.edigest.journal.exception.ResourceNotFoundException;
import com.edigest.journal.repository.JournalRepository;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;

    public void saveJournal(Journal journal) {
        journal.setCreatedAt(Instant.now());
        journalRepository.save(journal);
    }

    public List<Journal> findAllJournals() {
        return journalRepository.findAll();
    }

    public Journal findJournalById(ObjectId id) {
        return journalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Journal", "id", id));
    }
}
