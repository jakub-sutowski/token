package com.example.token.service;

import com.example.token.model.request.RegisterRequest;
import com.example.token.model.response.TokenResponse;
import com.example.token.exception.exceptions.UserNotExist;
import com.example.token.model.entity.User;
import com.example.token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void addUserFromAllegro(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .build();
        userRepository.save(user);
    }

    @Transactional
    public TokenResponse generateToken(String email) {
        String token = UUID.randomUUID().toString();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotExist(email));
        user.setToken(token);
        userRepository.save(user);
        return new TokenResponse(token);
    }
}
