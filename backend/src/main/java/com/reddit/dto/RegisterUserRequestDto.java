package com.reddit.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Data
public class RegisterUserRequestDto {
    @NotBlank(message = "name cannot be blank")
    private String name;
    @NotBlank(message = "email cannot be blank")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
}
