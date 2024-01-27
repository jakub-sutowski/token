package com.example.token.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum StatusCode {
    CORRECT_TOKEN,
    INVALID_TOKEN,
    USER_CREATED,
    USER_ALREADY_EXIST
}
