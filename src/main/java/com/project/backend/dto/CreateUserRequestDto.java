package com.project.backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Getter
public class CreateUserRequestDto {

    private String userId;
    @NotNull(message = "Password is Required")
    private String password;
    private String nickName;
    private String name;
    private String email;
    private Date birth;
}
