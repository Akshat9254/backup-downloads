package com.reddit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateCommentRequestDto {
    @NotBlank(message = "comment text cannot be blank")
    @Size(max = 1000, message = "comment text cannot be more than 1000 characters")
    private String text;
    @NotNull(message = "userId required")
    private Long userId;
    @NotNull(message = "postId required")
    private Long postId;
    private Long parentId;
}
