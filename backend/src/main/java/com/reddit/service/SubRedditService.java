package com.reddit.service;

import com.reddit.dto.CreateSubRedditRequestDto;
import com.reddit.exception.ResourceNotFoundException;
import com.reddit.model.SubReddit;
import com.reddit.model.User;
import com.reddit.repository.SubRedditRepository;
import com.reddit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubRedditService {
    private final SubRedditRepository subRedditRepository;
    private final UserRepository userRepository;
    private ModelMapper modelMapper;

    public void createCommunity(CreateSubRedditRequestDto communityRequestDto) {
        String title = communityRequestDto.getTitle();
        Long adminId = communityRequestDto.getAdminId();

        User admin = userRepository.findById(adminId)
                        .orElseThrow(() -> new ResourceNotFoundException("user", "id", adminId.toString()));

        SubReddit subReddit = new SubReddit();
        subReddit.setTitle(title);
        subReddit.setAdmin(admin);
        subReddit.getParticipants().add(admin);

        subRedditRepository.save(subReddit);
    }

}
