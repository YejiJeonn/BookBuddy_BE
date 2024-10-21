package com.project.backend.controller;

import com.project.backend.dto.CreateUserRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// 재료만 받음 -> service에서 가공 -> repo 가공된 정보 저장
@RestController     // json만 반환, rest 규약을 잘 지킨 api
public class UserController {

    @PostMapping("/signup")
    // ResponseEntity<반환하고싶은 타입>
    // -> 요청한 자에게 반환하고 싶은 값 (String일 경우 요청자에게 String값만 반환됨)
    public ResponseEntity<CreateUserRequestDto> signup(@RequestBody CreateUserRequestDto request, @RequestParam String name) {
        //

        return ResponseEntity.ok(request);
    }
}