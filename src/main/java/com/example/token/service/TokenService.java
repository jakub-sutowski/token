package com.example.token.service;

import com.example.token.exception.exceptions.UserNotExist;
import com.example.token.model.entity.User;
import com.example.token.model.request.ValidateRequest;
import com.example.token.model.response.StatusResponse;
import com.example.token.repository.UserRepository;
import com.example.token.type.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final UserRepository userRepository;

    public StatusResponse valid(ValidateRequest request) {
        String email = request.getEmail();
        User user = userRepository.findByEmail(email).orElseThrow(() -> new UserNotExist(email));
        String token = user.getToken();

        if (request.getToken().equals(token)) {
            return StatusResponse.builder().statusCode(StatusCode.CORRECT_TOKEN).build();
        } else {
            return StatusResponse.builder().statusCode(StatusCode.INVALID_TOKEN).build();
        }
    }
}
