package com.reddit.controller;

import com.reddit.dto.CreateSubRedditRequestDto;
import com.reddit.service.SubRedditService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/community")
@RequiredArgsConstructor
@Slf4j
public class SubRedditController {
    private final SubRedditService subRedditService;

    @PostMapping
    public void createCommunity(@RequestBody @Valid CreateSubRedditRequestDto communityRequestDto) {
        log.info("POST: createCommunity");
        subRedditService.createCommunity(communityRequestDto);
    }

}