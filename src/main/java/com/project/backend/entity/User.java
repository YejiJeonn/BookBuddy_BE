package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private String userId;     // 아이디

    private String password;          // 비밀번호
    private String nickname;     // 닉네임
    private String name;        // 이름
    private String email;       // 이메일
    private Date birth;         // 생년월일
}
