package com.reddit.service;

import com.reddit.dto.CommentDto;
import com.reddit.dto.CreateCommentRequestDto;
import com.reddit.exception.ResourceNotFoundException;
import com.reddit.model.Comment;
import com.reddit.model.Post;
import com.reddit.model.User;
import com.reddit.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;
    private final ModelMapper modelMapper;

    @Transactional
    public void createComment(CreateCommentRequestDto commentRequestDto) {
        Comment comment = modelMapper.map(commentRequestDto, Comment.class);
        User user = userService.findById(commentRequestDto.getUserId());
        Post post = postService.findById(commentRequestDto.getPostId());

        if(commentRequestDto.getParentId() != null) {
            Optional<Comment> parentComment = commentRepository.findById(commentRequestDto.getParentId());
            if(parentComment.isPresent()) {
                comment.setParent(parentComment.get());
                parentComment.get().getReplies().add(comment);
            }
        }

        comment.setUser(user);
        comment.setPost(post);
        commentRepository.save(comment);
    }

    @Transactional(readOnly = true)
    public Comment findById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new ResourceNotFoundException("comment", "id", commentId.toString()));
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getCommentsByPost(Long postId) {
        Post post = postService.findById(postId);
        List<Comment> comments = commentRepository.findAllByParentIsNullAndPost(post);
        return comments.stream().map((comment) -> modelMapper.map(comment, CommentDto.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<CommentDto> getRepliesByComment(Long commentId) {
        Comment comment = findById(commentId);
        List<Comment> replies = commentRepository.findAllByParent(comment);
        return replies.stream().map((reply) -> modelMapper.map(reply, CommentDto.class))
                .collect(Collectors.toList());
    }
}
