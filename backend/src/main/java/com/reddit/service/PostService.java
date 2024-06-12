package com.reddit.service;

import com.reddit.dto.CreatePostRequestDto;
import com.reddit.exception.ResourceNotFoundException;
import com.reddit.model.Post;
import com.reddit.model.SubReddit;
import com.reddit.model.User;
import com.reddit.repository.PostRepository;
import com.reddit.repository.SubRedditRepository;
import com.reddit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final SubRedditRepository subRedditRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public void createPost(CreatePostRequestDto postRequestDto) {
        Post post = modelMapper.map(postRequestDto, Post.class);
        User user = userRepository.findById(postRequestDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("user", "id",
                        postRequestDto.getUserId().toString()));
        SubReddit subReddit = subRedditRepository.findById(postRequestDto.getSubRedditId())
                .orElseThrow(() -> new ResourceNotFoundException("subReddit", "id",
                        postRequestDto.getSubRedditId().toString()));

        post.setUser(user);
        post.setSubReddit(subReddit);

        postRepository.save(post);
    }

    @Transactional(readOnly = true)
    public Post findById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("post", "id", postId.toString()));
    }

}
