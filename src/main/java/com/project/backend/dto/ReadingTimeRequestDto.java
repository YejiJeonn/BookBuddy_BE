package com.project.backend.dto;

import lombok.Getter;

import java.sql.Time;
import java.time.LocalDateTime;

@Getter
public class ReadingTimeRequestDto {

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Time totalTime;
    private String bookTitle;
}
