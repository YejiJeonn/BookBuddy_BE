package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "board")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num")
    private Long num;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "create_at")
    @CreationTimestamp
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate createAt;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "isbn")
    private String isbn;

    public void setPost(Long num, String userId, String title, String content, String nickname, String isbn) {
        this.num = num;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.isbn = isbn;
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
