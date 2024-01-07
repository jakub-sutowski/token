package com.example.token.controller;

import com.example.token.model.request.RegisterRequest;
import com.example.token.model.request.TokenRequest;
import com.example.token.model.request.ValidateRequest;
import com.example.token.model.response.TokenResponse;
import com.example.token.model.response.ValidateResponse;
import com.example.token.service.TokenService;
import com.example.token.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/token")
public class TokenController {

    private final UserService userService;
    private final TokenService tokenService;

    @PostMapping("/register/allegro")
    public ResponseEntity<Void> addUserFromAllegro(@RequestBody RegisterRequest registerRequest) {
        userService.addUserFromAllegro(registerRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/generate")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody TokenRequest request) {
        TokenResponse tokenResponse = userService.generateToken(request.getEmail());
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/valid")
    public ResponseEntity<ValidateResponse> validateToken(@RequestBody ValidateRequest validateRequest) {
        ValidateResponse validateResponse = tokenService.valid(validateRequest);
        return ResponseEntity.ok(validateResponse);
    }
}
