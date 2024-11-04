package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "reading_time")
@Getter
public class ReadingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "total_time")
//    private LocalTime totalTime;
    private long totalTime;

    @Column(name = "book_title")
    private String bookTitle;

    public void setTimes(LocalDateTime startTime, LocalDateTime endTime, long totalTime) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalTime = totalTime;
    }

//    public void calculateTotalTime(LocalDateTime startTime, LocalDateTime endTime) {
//        this.startTime = startTime;
//        this.endTime = endTime;
//
//        if (startTime != null && endTime != null) {
//            // Duration을 사용해 두 시간의 차이를 계산
//            Duration duration = Duration.between(startTime, endTime);
//
//            // 초 단위로 경과 시간 가져오기
//            long seconds = duration.getSeconds();
//
//            // LocalTime으로 변환하여 반환 (00:00:00 ~ 23:59:59 범위 내)
//            this.totalTime = LocalTime.ofSecondOfDay(seconds);
//        } else {
//            throw new IllegalStateException("Start time and end time must be set");
//        }
//    }
}
