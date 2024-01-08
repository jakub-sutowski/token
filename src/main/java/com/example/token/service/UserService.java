package com.example.token.service;

import com.example.token.exception.exceptions.UserAlreadyExist;
import com.example.token.model.request.RegisterRequest;
import com.example.token.model.response.RegisterResponse;
import com.example.token.model.response.TokenResponse;
import com.example.token.exception.exceptions.UserNotExist;
import com.example.token.model.entity.User;
import com.example.token.repository.UserRepository;
import com.example.token.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserValidation userValidation;
    public static final String USER_CREATED_CODE = "5";
    public static final String USER_ALREADY_EXIST_CODE = "6";

    @Transactional
    public RegisterResponse addUserFromAllegro(RegisterRequest request) {
        String email = request.getEmail();
        try {
            userValidation.register(email);
            User user = User.builder()
                    .email(email)
                    .build();
            userRepository.save(user);
            return new RegisterResponse(USER_CREATED_CODE);
        } catch (Exception e) {
            return new RegisterResponse(USER_ALREADY_EXIST_CODE);
        }
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
