package com.project.backend.service;

import com.project.backend.dto.ReadingTimeRequestDto;
import com.project.backend.entity.ReadingTime;
import com.project.backend.repository.ReadingTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReadingTimeService {

    private final ReadingTimeRepository readingTimeRepository;

    // 읽기 시간을 저장
    public void saveReadingTime(Long id, String userId, ReadingTimeRequestDto request) {
        ReadingTime readingTime = new ReadingTime();

        readingTime.setProperty(id, userId, request.getBookTitle(), request.getBookIsbn(), request.getBookCover());
        readingTime.calculateTotalTime(request.getStartTime(), request.getEndTime());

        readingTimeRepository.save(readingTime);
    }

//    public void saveReadingTime(ReadingTimeRequestDto dto) {
//        ReadingTime readingTime = new ReadingTime();
//
//        readingTime.setTimes(dto.getStartTime(), dto.getEndTime(), dto.getTotalTime());
//
////        readingTime.calculateTotalTime(dto.getStartTime(), dto.getEndTime());
//
//        readingTimeRepository.save(readingTime);
//    }
}
