package com.project.backend.controller;

import com.project.backend.dto.ReadingTimeRequestDto;
import com.project.backend.entity.ReadingTime;
import com.project.backend.entity.User;
import com.project.backend.repository.ReadingTimeRepository;
import com.project.backend.repository.UserRepository;
import com.project.backend.security.TokenProvider;
import com.project.backend.service.ReadingTimeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReadingTimeController {

    private final ReadingTimeService readingTimeService;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final ReadingTimeRepository readingTimeRepository;

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

        // request에서 유저 ID와 독서 시간을 가져와서 저장
        readingTimeService.saveReadingTime(id, userId, request);

//        return ResponseEntity.ok("독서 기록 저장 성공");

        return new ResponseEntity<>("시간이 저장되었습니다.", HttpStatus.OK);
    }

    @GetMapping("/reading-times")
    public List<ReadingTime> getAllReadingTimes() {
        return readingTimeRepository.findAll();
    }
}
