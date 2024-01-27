package com.example.token.controller;

import com.example.token.model.request.ShopRequest;
import com.example.token.model.request.ValidateRequest;
import com.example.token.model.response.StatusResponse;
import com.example.token.model.response.TokenResponse;
import com.example.token.service.TokenService;
import com.example.token.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/token")
public class TokenController {

    private final UserService userService;
    private final TokenService tokenService;

    @Operation(
            summary = "Add user from Shop",
            description = "Registers a new user from Shop."
    )
    @PostMapping("/register/shop")
    public ResponseEntity<StatusResponse> addUserFromShop(@RequestBody ShopRequest request) {
        StatusResponse registerResponse = userService.addUserFromShop(request);
        return ResponseEntity.ok(registerResponse);
    }

    @Operation(
            summary = "Generate token",
            description = "Generates a token for the given email."
    )
    @PostMapping("/generate")
    public ResponseEntity<TokenResponse> generateToken(@RequestBody ShopRequest request) {
        TokenResponse tokenResponse = userService.generateToken(request.getEmail());
        return ResponseEntity.ok(tokenResponse);
    }

    @Operation(
            summary = "Validate token",
            description = "Validates the provided token."
    )
    @PostMapping("/valid")
    public ResponseEntity<StatusResponse> validateToken(@RequestBody ValidateRequest request) {
        StatusResponse response = tokenService.valid(request);
        return ResponseEntity.ok(response);
    }
}
