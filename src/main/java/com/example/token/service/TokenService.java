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

    private final UserRepository userRepository;

    private static final String CORRECT_TOKEN_CODE = "1";
    public static final String INVALID_TOKEN_CODE = "2";

    public ValidateResponse valid(ValidateRequest request) {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotExist(email));
        String token = user.getToken();

        if (request.getToken().equals(token)) {
            return ValidateResponse.builder().statusCode(CORRECT_TOKEN_CODE).build();
        } else {
            return ValidateResponse.builder().statusCode(INVALID_TOKEN_CODE).build();
        }
    }
}
