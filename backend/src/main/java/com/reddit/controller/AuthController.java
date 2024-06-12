package com.reddit.controller;

import com.reddit.dto.ApiResponse;
import com.reddit.dto.AuthenticationResponseDto;
import com.reddit.dto.LoginUserRequestDto;
import com.reddit.dto.RegisterUserRequestDto;
import com.reddit.enums.ResponseType;
import com.reddit.service.AuthService;
import com.reddit.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthenticationResponseDto>> registerUser(
            @RequestBody @Valid RegisterUserRequestDto userRequestDto) {
        log.info("POST: registerUser");
        AuthenticationResponseDto responseDto = authService.registerUser(userRequestDto);
        return new ResponseEntity<>(ApiResponse
                .<AuthenticationResponseDto>builder()
                .status(ResponseType.SUCCESS)
                .results(responseDto)
                .build(), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponseDto>> loginUser(
            @RequestBody @Valid LoginUserRequestDto userRequestDto) {
        log.info("POST: loginUser");
        AuthenticationResponseDto responseDto = authService.loginUser(userRequestDto);
        return new ResponseEntity<>(ApiResponse
                .<AuthenticationResponseDto>builder()
                .status(ResponseType.SUCCESS)
                .results(responseDto)
                .build(), HttpStatus.CREATED);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logoutUser() {
        log.info("POST: logoutUser");
        authService.logoutUser();
        return ResponseEntity.ok(ApiResponse.<String>builder()
                        .status(ResponseType.SUCCESS)
                        .results("User logged out successfully")
                        .build());
    }
}
