package com.project.backend.dto;

import lombok.Getter;

import java.util.Date;

@Getter
public class CreateUserRequestDto {

    private String userId;
    private String password;
    private String checkPassword;
    private String nickName;
    private String name;
    private String email;
    private Date birth;
}
