package com.edigest.journal.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateJournalRequestDto {
    @NotBlank(message = "title cannot be empty")
    @Max(value = 50, message = "title cannot be more than 50 characters")
    private String title;

    @NotBlank(message = "content cannot be empty")
    @Max(value = 500, message = "content cannot be more than 500 characters")
    private String content;
}
