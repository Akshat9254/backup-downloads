package com.edigest.journal.controller;

import com.edigest.journal.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health-check")
public class HealthCheckController {

    @GetMapping
    public ApiResponse<Void> healthCheck() {
        return new ApiResponse<>(true, HttpStatus.OK.value(),
                "Journal Service in up and running :)", null);
    }
}
