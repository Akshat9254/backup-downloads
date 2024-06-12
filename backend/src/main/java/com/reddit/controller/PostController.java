package com.reddit.controller;

import com.reddit.dto.CreatePostRequestDto;
import com.reddit.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    @PostMapping
    public void createPost(@RequestBody @Valid CreatePostRequestDto postRequestDto) {
        log.info("POST: createPost");
        postService.createPost(postRequestDto);
    }
}
