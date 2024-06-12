package com.reddit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateSubRedditRequestDto {
    @NotBlank(message = "title cannot be empty")
    @Size(max = 255, message = "title cannot be more than 255 characters")
    private String title;
    @NotNull(message = "adminId cannot be null")
    private Long adminId;
}
