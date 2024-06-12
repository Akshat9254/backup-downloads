package com.reddit.service;

import com.reddit.dto.AuthenticationResponseDto;
import com.reddit.dto.LoginUserRequestDto;
import com.reddit.dto.RegisterUserRequestDto;
import com.reddit.enums.RoleType;
import com.reddit.model.User;
import com.reddit.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Transactional
    public AuthenticationResponseDto registerUser(RegisterUserRequestDto userRequestDto) {
        User user = modelMapper.map(userRequestDto, User.class);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setRole(RoleType.USER);
        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(user);
        return AuthenticationResponseDto.builder()
                .token(token)
                .build();
    }

    public AuthenticationResponseDto loginUser(LoginUserRequestDto requestDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(),
                        requestDto.getPassword()
                )
        );

        User user = userService.findByEmail(requestDto.getEmail());
        String token = jwtService.generateToken(user);

        return AuthenticationResponseDto.builder()
                .token(token)
                .build();

    }

    public void logoutUser() {
        SecurityContextHolder.clearContext();
    }
}
