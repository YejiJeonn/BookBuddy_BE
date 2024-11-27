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

    @Column(name = "author")
    private String author;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "pub_date")
    private String pubDate;

    @Column(name = "cover")
    private String cover;

    public void setLibrary(Long num, String userId, String nickname, String title, String isbn, String author, String publisher, String pubDate, String cover) {
        this.num = num;
        this.userId = userId;
        this.nickname = nickname;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.pubDate = pubDate;
        this.cover = cover;
    }
}
