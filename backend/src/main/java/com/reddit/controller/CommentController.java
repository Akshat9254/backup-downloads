package com.reddit.controller;

import com.reddit.dto.ApiResponse;
import com.reddit.dto.CommentDto;
import com.reddit.dto.CreateCommentRequestDto;
import com.reddit.enums.ResponseType;
import com.reddit.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@Slf4j
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public void createComment(@RequestBody @Valid CreateCommentRequestDto commentRequestDto) {
        commentService.createComment(commentRequestDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<CommentDto>>> getCommentsByPost(@RequestParam Long postId) {
        List<CommentDto> comments = commentService.getCommentsByPost(postId);
        return new ResponseEntity<>(ApiResponse.<List<CommentDto>>builder()
                .status(ResponseType.SUCCESS)
                .results(comments)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/reply")
    public ResponseEntity<ApiResponse<List<CommentDto>>> getRepliesByComment(@RequestParam Long commentId) {
        List<CommentDto> replies = commentService.getRepliesByComment(commentId);
        return new ResponseEntity<>(ApiResponse.<List<CommentDto>>builder()
                .status(ResponseType.SUCCESS)
                .results(replies)
                .build(), HttpStatus.OK);
    }
}
