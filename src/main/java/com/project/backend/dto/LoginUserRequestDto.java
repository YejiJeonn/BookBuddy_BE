package com.project.backend.dto;

import lombok.Getter;

@Getter
public class LoginUserRequestDto {

    private String userId;
    private String password;

//    public LoginUserRequestDto(String userId, String password) {
//        this.userId = userId;
//        this.password = password;
//    }
}
