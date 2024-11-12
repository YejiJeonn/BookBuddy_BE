package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "reading_time")
@Getter
public class ReadingTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num")
    private Long num;

    @Column(name = "user_id")
    private String userId;

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

    @Column(name = "book_isbn")
    private String bookIsbn;


//    public void setTimes(LocalDateTime startTime, LocalDateTime endTime, long totalTime) {
//        this.startTime = startTime;
//        this.endTime = endTime;
//        this.totalTime = totalTime;
//    }

    public void setProperty(Long num, String userId, String bookTitle, String bookIsbn) {
        this.num = num;
        this.userId = userId;
        this.bookTitle = bookTitle;
        this.bookIsbn = bookIsbn;
    }

    public void calculateTotalTime(LocalDateTime startTime, LocalDateTime endTime) {
        this.startTime = startTime;
        this.endTime = endTime;

        if (startTime != null && endTime != null) {
            // Duration을 사용해 두 시간의 차이를 계산
            Duration duration = Duration.between(startTime, endTime);

            // 초 단위로 경과 시간 가져오기
            this.totalTime = duration.getSeconds();
        } else {
            throw new IllegalStateException("Start time and end time must be set");
        }
    }
}
