package com.reddit.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreatePostRequestDto {
    @Size(max = 255, message = "title cannot be more than 255 characters")
    private String title;
    @Size(max = 1000, message = "title cannot be more than 255 characters")
    private String text;
    @NotNull(message = "userId required")
    private Long userId;
    @NotNull(message = "subRedditId required")
    private Long subRedditId;
}
