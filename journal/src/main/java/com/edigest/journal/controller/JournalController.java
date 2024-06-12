package com.edigest.journal.controller;

import com.edigest.journal.converter.CreateJournalRequestDtoToJournalConverter;
import com.edigest.journal.converter.JournalToJournalDtoConverter;
import com.edigest.journal.dto.CreateJournalRequestDto;
import com.edigest.journal.dto.JournalDto;
import com.edigest.journal.entity.Journal;
import com.edigest.journal.response.ApiResponse;
import com.edigest.journal.service.JournalService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/journals")
@RequiredArgsConstructor
public class JournalController {
    private final JournalService journalService;
    private final JournalToJournalDtoConverter journalToJournalDtoConverter;
    private final CreateJournalRequestDtoToJournalConverter createJournalRequestDtoToJournalConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Void> saveJournal(@RequestBody CreateJournalRequestDto journalDto) {
        Journal journal = createJournalRequestDtoToJournalConverter.convert(journalDto);
        journalService.saveJournal(journal);
        return new ApiResponse<>(true, HttpStatus.CREATED.value(),
                "journal created successfully", null);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<List<JournalDto>> findAllJournals() {
        List<Journal> journals = journalService.findAllJournals();
        List<JournalDto> journalDtos = journals.stream()
                .map(journalToJournalDtoConverter::convert).toList();

        return new ApiResponse<>(true, HttpStatus.OK.value(),
                "fetch all journals successful", journalDtos);
    }

    @GetMapping("/{journalId}")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<JournalDto> findJournalById(@PathVariable("journalId") ObjectId journalId) {
        Journal journal = journalService.findJournalById(journalId);
        JournalDto journalDto = journalToJournalDtoConverter.convert(journal);
        return new ApiResponse<>(true, HttpStatus.OK.value(),
                String.format("fetch journal with id:%s successful", journalId), journalDto);
    }
}
