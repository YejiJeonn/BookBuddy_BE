package com.project.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;     // 아이디
    private String pw;          // 비밀번호
    private String nickName;     // 닉네임
    private String name;        // 이름
    private String email;       // 이메일
    private Date birth;         // 생년월일

    // 비밀번호 암호화
//    public User toEntity(String encodedPassword) {
//        return User.builder()
//                .loginId(this.loginId)
//                .password(encodedPassword)
//                .nickname(this.nickname)
//                .role(UserRole.USER)
//                .build();
//    }
}
