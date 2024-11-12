package com.project.backend.controller;

import com.project.backend.dto.ReadingTimeRequestDto;
import com.project.backend.entity.User;
import com.project.backend.repository.UserRepository;
import com.project.backend.security.TokenProvider;
import com.project.backend.service.ReadingTimeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReadingTimeController {

    private final ReadingTimeService readingTimeService;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;

    @PostMapping("/save-reading-time")
    public ResponseEntity<?> saveTime(@RequestBody ReadingTimeRequestDto request, HttpServletRequest httpServletRequest) {

        // JWT 토큰 검증
        String token = httpServletRequest.getHeader("Authorization").substring(7);

        if (token == null) {
            return new ResponseEntity<>("로그인 해주세요.", HttpStatus.UNAUTHORIZED);
        }

        Long id = tokenProvider.getSubject(token);
        User user = userRepository.findUserById(id);
        String userId = user.getUserId();

        if (user == null) {
            return new ResponseEntity<>("유효하지 않은 사용자", HttpStatus.UNAUTHORIZED);
        }

//        // DB 테이블에서 해당 사용자 ID가 있는지 확인
//        if (readingTimeService.isUserTableExists(user.getId())) {
//            readingTimeService.saveReadingTime(user.getId(), request);
//        } else {
//            readingTimeService.createUserTable(user.getId());
//            readingTimeService.saveReadingTime(user.getId(), request);
//        }

        // request에서 유저 ID와 독서 시간을 가져와서 저장
        readingTimeService.saveReadingTime(id, userId, request);

//        return ResponseEntity.ok("독서 기록 저장 성공");

        return new ResponseEntity<>("시간이 저장되었습니다.", HttpStatus.OK);
    }
}
