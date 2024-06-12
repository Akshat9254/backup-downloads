package com.edigest.journal.converter;

import com.edigest.journal.dto.CreateJournalRequestDto;
import com.edigest.journal.entity.Journal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class CreateJournalRequestDtoToJournalConverter implements Converter<CreateJournalRequestDto, Journal> {
    @Override
    @NonNull
    public Journal convert(@NonNull CreateJournalRequestDto source) {
        return Journal.builder()
                .title(source.getTitle())
                .content(source.getContent())
                .createdAt(Instant.now())
                .build();
    }
}
