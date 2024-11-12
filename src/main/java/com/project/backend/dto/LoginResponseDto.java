package com.project.backend.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginResponseDto {

    private final String nickName;
    private final String token;

//    public LoginResponseDto(String nickName, String token) {
//        this.nickName = nickName;
//        this.token = token;
//    }
}
