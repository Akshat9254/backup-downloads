package com.edigest.journal.converter;

import com.edigest.journal.dto.JournalDto;
import com.edigest.journal.entity.Journal;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class JournalToJournalDtoConverter implements Converter<Journal, JournalDto> {
    @Override
    @NonNull
    public JournalDto convert(@NonNull Journal source) {
        return JournalDto.builder()
                .id(source.getId())
                .title(source.getTitle())
                .content(source.getContent())
                .createdAt(source.getCreatedAt())
                .build();
    }
}
