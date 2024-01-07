package com.example.token.service;

import com.example.token.model.request.ValidateRequest;
import com.example.token.model.response.ValidateResponse;
import com.example.token.exception.exceptions.UserNotExist;
import com.example.token.model.entity.User;
import com.example.token.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private static final String TOKEN_CORRECT_CODE = "1";
    private final UserRepository userRepository;

    public ValidateResponse valid(ValidateRequest request) {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotExist(email));
        String token = user.getToken();
//        LocalDateTime tokenCreationDate = user.getTokenCreationDate();
        if (request.getToken().equals(token)) {
            return ValidateResponse.builder().statusCode(TOKEN_CORRECT_CODE).build();
        } else {
            return ValidateResponse.builder().statusCode("2").build();
        }
    }
}
