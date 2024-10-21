package com.project.backend.controller;

import com.project.backend.dto.CreateUserRequestDto;
import com.project.backend.dto.LoginUserRequestDto;
import com.project.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 재료만 받음 -> service에서 가공 -> repo 가공된 정보 저장
@RestController     // json만 반환, rest 규약을 잘 지킨 api
public class UserController {

    @Autowired
    public UserService userService;

    // 회원가입
    @PostMapping("/signup")
    // ResponseEntity<반환하고싶은 타입>
    // -> 요청한 자에게 반환하고 싶은 값 (String일 경우 요청자에게 String값만 반환됨)
    public ResponseEntity<String> signup(@RequestBody CreateUserRequestDto request) {

        userService.saveUser(request);

        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginUserRequestDto request) {

        boolean loginResult = userService.loginUser(request);
        String name = userService.infoUser(request).getName();

        if(loginResult) {
            return ResponseEntity.ok(name + " 님 환영합니다.");
        }
        return ResponseEntity.ok("failed login");
    }

    // 아이디 중복 확인

    // 닉네임 중복 확인

    // 비밀번호 확인
}