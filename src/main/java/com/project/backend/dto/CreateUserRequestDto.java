package com.project.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@Getter
public class CreateUserRequestDto {

    @NotBlank(message="로그인 아이디를 입력해주세요.")
    private String userId;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    private String checkPassword;

    @NotBlank(message = "닉네임을 입력해주세요.")
    private String nickName;
    private String name;
    private String email;
    private Date birth;
}
