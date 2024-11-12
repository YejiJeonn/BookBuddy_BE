package com.project.backend.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReadingTimeRequestDto {

    private Long num;
    private String userId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    //    private Time totalTime;
    private long totalTime;     // 밀리초로 된 읽기 시간
    private String bookTitle;
    private String bookIsbn;
}
