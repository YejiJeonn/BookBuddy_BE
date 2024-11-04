package com.project.backend.controller;

import com.project.backend.dto.ReadingTimeRequestDto;
import com.project.backend.service.ReadingTimeService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/save-reading-time")
    public ResponseEntity<String> saveTime(@RequestBody ReadingTimeRequestDto request) {
        // request에서 유저 ID와 독서 시간을 가져와서 저장
        readingTimeService.saveReadingTime(request);

        return ResponseEntity.ok("독서 기록 저장 성공");
    }
}
