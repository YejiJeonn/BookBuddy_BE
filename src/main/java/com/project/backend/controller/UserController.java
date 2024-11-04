package com.project.backend.controller;

import com.project.backend.dto.CreateUserRequestDto;
import com.project.backend.dto.LoginUserRequestDto;
import com.project.backend.repository.UserRepository;
import com.project.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 재료만 받음 -> service에서 가공 -> repo 가공된 정보 저장
@RestController     // json만 반환, rest 규약을 잘 지킨 api
@RequiredArgsConstructor
public class UserController {

    public final UserService userService;

    private final UserRepository userRepository;

    // 회원가입
    @PostMapping("/users/signup")
    // ResponseEntity<반환하고싶은 타입>
    // -> 요청한 자에게 반환하고 싶은 값 (String일 경우 요청자에게 String값만 반환됨)
    public ResponseEntity<String> signup(@RequestBody CreateUserRequestDto request) {

        userService.saveUser(request);

        return ResponseEntity.ok("회원가입 성공");
    }

    // 로그인
    @PostMapping("/users/login")
    public ResponseEntity<String> login(@RequestBody LoginUserRequestDto request) {

        String userName = userService.loginUser(request);

        if (userName == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인 실패");
        }


        // 로그인 성공일 경우 이용자의 이름을 가져와서 문구 출력
        return ResponseEntity.ok(userName + " 님 환영합니다.");
    }

    // 아이디 중복 확인
    @PostMapping("/users/check-id")
    public ResponseEntity<Boolean> checkIdDuplicate(@RequestParam String userId) {

        boolean result = userService.isUserIdDuplicate(userId);

        return ResponseEntity.ok(result);
    }

    // 닉네임 중복 확인
    @PostMapping("/users/check-nickname")
    public ResponseEntity<Boolean> checkNickNameDuplicate(@RequestParam String nickName) {

        boolean result = userService.existsByNickName(nickName);

        return ResponseEntity.ok(result);
    }
}