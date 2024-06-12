package com.reddit.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginUserRequestDto {
    @Email(message = "invalid email")
    private String email;
    @NotBlank(message = "password cannot be blank")
    private String password;
}
