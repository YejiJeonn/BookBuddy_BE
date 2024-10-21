package com.project.test.controller;

import com.project.test.dto.CreateUserRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController     // json만 반환, rest 규약을 잘 지킨 api
public class UserController {

    @PostMapping("/signup")
    public String signup(@RequestBody CreateUserRequestDto request) {
        System.out.printf(request.toString());

        return null;
    }
}