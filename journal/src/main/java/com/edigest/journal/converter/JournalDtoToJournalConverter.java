package com.edigest.journal.converter;

import com.edigest.journal.dto.JournalDto;
import com.edigest.journal.entity.Journal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class JournalDtoToJournalConverter implements Converter<JournalDto, Journal> {
    @Override
    @NonNull
    public Journal convert(@NonNull JournalDto source) {
        return Journal.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .build();
    }
}
