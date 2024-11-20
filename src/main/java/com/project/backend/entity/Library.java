package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "book_history")
@Getter
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num")
    private Long num;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "title")
    private String title;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "comment")
    private String comment;

    public void setLibrary(Long num, String userId, String nickname, String title, String isbn, String comment) {
        this.num = num;
        this.userId = userId;
        this.nickname = nickname;
        this.title = title;
        this.isbn = isbn;
        this.comment = comment;
    }
}
