package com.example.token.service;

import com.example.token.exception.exceptions.UserAlreadyExist;
import com.example.token.exception.exceptions.UserNotExist;
import com.example.token.model.entity.User;
import com.example.token.model.request.ShopRequest;
import com.example.token.model.response.StatusResponse;
import com.example.token.model.response.TokenResponse;
import com.example.token.repository.UserRepository;
import com.example.token.type.StatusCode;
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

    @Transactional
    public StatusResponse addUserFromShop(ShopRequest request) {
        String email = request.getEmail();
        try {
            userValidation.register(email);
            User user = User.builder()
                    .email(email)
                    .build();
            userRepository.save(user);
            return new StatusResponse(StatusCode.USER_CREATED);
        } catch (UserAlreadyExist e) {
            return new StatusResponse(StatusCode.USER_ALREADY_EXIST);
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
