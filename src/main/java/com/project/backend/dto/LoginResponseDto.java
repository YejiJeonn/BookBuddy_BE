package com.project.backend.dto;

import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String nickname;
    private String token;

    public LoginResponseDto(String nickname, String token) {
        this.nickname = nickname;
        this.token = token;
    }
}
